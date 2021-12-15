package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.dao.CartDataBaseAccess;
import ir.maktab58.onlineshop.dao.CustomerDataBaseAccess;
import ir.maktab58.onlineshop.dao.ProductDataBaseAccess;
import ir.maktab58.onlineshop.enumation.ElectronicDevicesTypes;
import ir.maktab58.onlineshop.enumation.ProductType;
import ir.maktab58.onlineshop.enumation.ReadingItemsTypes;
import ir.maktab58.onlineshop.exceptions.EmptyBufferException;
import ir.maktab58.onlineshop.exceptions.IllegalInput;
import ir.maktab58.onlineshop.exceptions.OnlineShopExceptions;
import ir.maktab58.onlineshop.models.Admin;
import ir.maktab58.onlineshop.models.Cart;
import ir.maktab58.onlineshop.models.Customer;
import ir.maktab58.onlineshop.models.products.Product;
import ir.maktab58.onlineshop.service.singletonvalidator.NationalCodeValidator;
import ir.maktab58.onlineshop.service.singletonvalidator.UserAndPassValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class OnlineShop implements OnlineShopInterface {
    private final Admin admin = new Admin();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> costumers = new ArrayList<>();
    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();
    private final CustomerService customerService = new CustomerService();
    private final Scanner scanner = new Scanner(System.in);

    /*public void updateOnlineShopProperties() {
        costumers = customerService.getAllCustomers();
    }

    public void updateOnlineShopProperties(String type) {
        if (type.equals(ProductType.SHOE.getType())) {
            products = productDataBaseAccess.getAllShoes();
        }
        if (type.equals(ElectronicDevicesTypes.TELEVISION.getType())) {
            products = productDataBaseAccess.getAllTelevisions();
        }
        if (type.equals(ElectronicDevicesTypes.RADIO.getType())) {
            products = productDataBaseAccess.getAllRadios();
        }
        if (type.equals(ReadingItemsTypes.BOOK.getType())) {
            products = productDataBaseAccess.getAllBooks();
        }
        if (type.equals(ReadingItemsTypes.MAGAZINE.getType())) {
            products = productDataBaseAccess.getAllMagazines();
        }
        throw new IllegalInput("The type that you entered is not allowed.", 400);
    }*/

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
        return customer.getId();
    }

    /*

    private void addReadingItemsToCart(int customerId) {
        System.out.println("Which one would you like to add? Book/Magazine");
        Scanner scanner = new Scanner(System.in);
        String typeOfReading = scanner.nextLine().trim();
        if (ReadingItemsTypes.MAGAZINE.getType().equals(typeOfReading))
            addItemToCart(customerId, ReadingItemsTypes.MAGAZINE.getType());
        else if (ReadingItemsTypes.BOOK.getType().equals(typeOfReading))
            addItemToCart(customerId, ReadingItemsTypes.BOOK.getType());
        else
            System.out.println("Invalid type of input, please try again.");
    }

    private void addElectronicDevicesToCart(int customerId) {
        System.out.println("Which one would you like to add? Television/Radio");
        Scanner scanner = new Scanner(System.in);
        String deviceType = scanner.nextLine().trim();
        if (deviceType.equalsIgnoreCase(ElectronicDevicesTypes.TELEVISION.getType()))
            addItemToCart(customerId, ElectronicDevicesTypes.TELEVISION.getType());
        else if (deviceType.equalsIgnoreCase(ElectronicDevicesTypes.RADIO.getType()))
            addItemToCart(customerId, ElectronicDevicesTypes.RADIO.getType());
        else
            System.out.println("Invalid type of input, please try again.");
    }

    private void addItemToCart(int customerId, String type){
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
