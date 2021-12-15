package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.enumation.ElectronicDevicesTypes;
import ir.maktab58.onlineshop.enumation.ReadingItemsTypes;
import ir.maktab58.onlineshop.exceptions.OnlineShopExceptions;
import ir.maktab58.onlineshop.models.Admin;
import ir.maktab58.onlineshop.models.Cart;
import ir.maktab58.onlineshop.models.Customer;
import ir.maktab58.onlineshop.models.products.Product;
import ir.maktab58.onlineshop.models.products.electronicdevices.ElectronicDevices;
import ir.maktab58.onlineshop.models.products.readingItems.Book;
import ir.maktab58.onlineshop.models.products.readingItems.Magazine;
import ir.maktab58.onlineshop.models.products.readingItems.ReadingItems;
import ir.maktab58.onlineshop.service.singletonvalidator.NationalCodeValidator;
import ir.maktab58.onlineshop.service.singletonvalidator.UserAndPassValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * @author Taban Soleymani
 */
public class OnlineShopService implements OnlineShopInterface {
    private List<Product> products;
    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();
    private final CustomerService customerService = new CustomerService();

    public int registerCustomer(String inputLine) {
        Customer newCustomer = getCustomerInformation(inputLine);
        List<Customer> listOfExistedCustomersByThisUser = customerService
                .getListOfExistedCustomersByThisUsername(newCustomer.getUsername());
        List<Customer>  listOfExistedCustomersByThisNationalCode = customerService
                .getListOfExistedCustomersByThisNationalCode(newCustomer.getNationalCode());
        if (listOfExistedCustomersByThisUser.size() != 0)
            throw OnlineShopExceptions.builder()
                    .message("This username " + newCustomer.getUsername() + " is already existed")
                    .errorCode(400).build();
        if (listOfExistedCustomersByThisNationalCode.size() != 0)
            throw OnlineShopExceptions.builder()
                    .message("This nationalCode " + newCustomer.getNationalCode() + " is already existed")
                    .errorCode(400).build();
        return customerService.saveNewCustomer(newCustomer);
    }

    private Customer getCustomerInformation(String inputLine) {
        String[] inputTokens = inputLine.split(" ");
        String name = inputTokens[0];
        String family = inputTokens[1];
        String username = inputTokens[2];
        String password = inputTokens[3];
        long initialBalance = Long.parseLong(inputTokens[4]);
        String nationalCode = inputTokens[5];
        int birthYear = Integer.parseInt(inputTokens[6]);
        validateUserPassAndNationalCode(username, password, nationalCode);
        return Customer.builder()
                .withName(name)
                .withFamily(family)
                .withUsername(username)
                .withPassword(password)
                .withInitialBalance(initialBalance)
                .withNationalCode(nationalCode)
                .withBirthYear(birthYear).build();
    }

    private void validateUserPassAndNationalCode(String username, String password, String nationalCode) {
        boolean isNationalCodeValid = NationalCodeValidator.getInstance().isNationalCodeValid(nationalCode);
        boolean isUserAndPassValid = UserAndPassValidator.getInstance().isUserAndPassValid(username, password);
        if (!isNationalCodeValid)
            throw OnlineShopExceptions.builder()
                    .message("The entered national code is not valid.")
                    .errorCode(400).build();
        if (!isUserAndPassValid)
            throw OnlineShopExceptions.builder()
                    .message("The entered username or pass is not valid.")
                    .errorCode(400).build();
    }

    public int loginAsACustomer(String username, String password) {
        Customer customer = customerService.getCustomerByUserAndPass(username, password);
        if (customer == null)
            return 0;
        return customer.getId();
    }

    public List<Product> getReadingItems(String typeOfReading) {
        if (ReadingItemsTypes.MAGAZINE.getType().equalsIgnoreCase(typeOfReading)) {
            products = productService.getMagazines();
            return products;
        }
        if (ReadingItemsTypes.BOOK.getType().equalsIgnoreCase(typeOfReading)) {
            products =  productService.getBooks();
            return products;
        }
        throw OnlineShopExceptions.builder()
                .message("Invalid type of reading Item: " + typeOfReading)
                .errorCode(400).build();
    }

    public List<Product> getShoes() {
        products = productService.getShoes();
        return products;
    }

    public List<Product> getElectronicDevices(String typeOfDevice) {
        if (ElectronicDevicesTypes.TELEVISION.getType().equalsIgnoreCase(typeOfDevice)) {
            products = productService.getTVs();
            return products;
        }
        if (ElectronicDevicesTypes.RADIO.getType().equalsIgnoreCase(typeOfDevice)) {
            products = productService.getRadios();
            return products;
        }
        throw OnlineShopExceptions.builder()
                .message("Invalid type of electronc-device Item: " + typeOfDevice)
                .errorCode(400).build();
    }

    public int addItemToCart(int customerId, int productId, int count) {
        Optional<Product> foundedProduct = products.stream().filter(product -> productId == product.getId()).findFirst();
        if (foundedProduct.isEmpty())
            throw OnlineShopExceptions.builder()
                    .message(productId + " is not existed.")
                    .errorCode(400).build();
        Product product = foundedProduct.get();
        if (count < product.getCount())
            throw OnlineShopExceptions.builder()
                    .message(count + "s is not existed from this product.")
                    .errorCode(400).build();
        Customer customer = customerService.getCustomerById(customerId);
        Cart cart = Cart.builder()
                .withCustomer(customer)
                .withProduct(product)
                .withQuantity(count).build();
        return cartService.saveCart(cart);
    }

    /*private void addItemToCart(int customerId, String type){
        updateOnlineShopProperties(type);
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("Enter id of Item that you want add it to your cart.");
        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine().trim());
        if (id > products.size()) {
            System.out.println("Invalid Id, please try again.");
            return;
        }
        System.out.println("Enter count of Items that you want add it to your cart.");
        int count = Integer.parseInt(scanner.nextLine().trim());
        if (count > products.get(id - 1).getCount()) {
            System.out.println("Sorry! There is not enough shoes with id:" + id + " in our storage.");
            return;
        }
        Cart cart = new Cart(costumers.get(customerId - 1));
        cart.addNewProduct(products.get(id - 1));
        CartDataBaseAccess cartAccess = new CartDataBaseAccess();
        boolean isAdded = cartAccess.saveCart(cart);
        if (isAdded)
            System.out.println("This item is added successfully to your cart.");
    }

    @Override
    public void deleteProductFromCart(int customerId, Cart cart) {
        System.out.println(cart);
        System.out.println("Enter the productId that you want to delete.");
        Scanner scanner = new Scanner(System.in);
        int productId = Integer.parseInt(scanner.nextLine().trim());
        boolean isExisted = false;
        for (Product product : cart.getProducts()) {
            if (product.getId() == productId) {
                isExisted = true;
                break;
            }
        }
        if (!isExisted) {
            System.out.println("This productId:" + productId + "is not existed.");
            return;
        }
        CartDataBaseAccess cartAccess = new CartDataBaseAccess();
        cartAccess.deleteARow(customerId, productId);
    }

    @Override
    public void printAddedItemsWithPriceToCart(Cart cart, long totalPrice) {
        ArrayList<Product> productsInCart = cart.getProducts();
        for (Product product : productsInCart) {
            System.out.println(product);
        }
        System.out.println("********************");
        System.out.println("Total Price: " + totalPrice);
    }

    private long calcTotalPrice(Cart cart){
        long totalPrice = 0;
        ArrayList<Product> productsInCart = cart.getProducts();
        for (Product product : productsInCart) {
            totalPrice = product.getCount() * product.getPrice();
        }
        return totalPrice;
    }

    @Override
    public void confirmShopping(Cart cart, long totalPrice, int customerId) {
        updateOnlineShopProperties();
        long balance = costumers.get(customerId - 1).getInitialBalance();
        if (balance < totalPrice) {
            System.out.println("You should deposit your balance first.");
        } else {
            System.out.println("Your balance is enough.");
            customerAccess.updateCustomerBalance(customerId, (long) balance - totalPrice);
            ArrayList<Product> products = cart.getProducts();
            this.products = productDataBaseAccess.getAllProducts();
            for (Product productInCart : products) {
                for (Product product : this.products) {
                    if (product.equals(productInCart)) {
                        int count = product.getCount() - productInCart.getCount();
                        productDataBaseAccess.updateCountOfProducts(product, count);
                        CartDataBaseAccess cartAccess = new CartDataBaseAccess();
                        cartAccess.deleteARow(customerId, product.getId());
                    }
                }
            }
        }
    }

    private void depositCustomerBalance(int customerId){
        updateOnlineShopProperties();
        System.out.println("How much do you want to deposit your account?");
        Scanner scanner = new Scanner(System.in);
        long amount = Long.parseLong(scanner.nextLine().trim());
        customerAccess.updateCustomerBalance(customerId, costumers.get(customerId - 1).getInitialBalance() + amount);
    }*/
}
