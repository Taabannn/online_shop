package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.dao.CartDataBaseAccess;
import ir.maktab58.onlineshop.dao.CustomerDataBaseAccess;
import ir.maktab58.onlineshop.dao.ProductDataBaseAccess;
import ir.maktab58.onlineshop.enumation.ElectronicDevicesTypes;
import ir.maktab58.onlineshop.enumation.ProductType;
import ir.maktab58.onlineshop.enumation.ReadingItemsTypes;
import ir.maktab58.onlineshop.exceptions.EmptyBufferException;
import ir.maktab58.onlineshop.exceptions.IllegalInput;
import ir.maktab58.onlineshop.models.Admin;
import ir.maktab58.onlineshop.models.Cart;
import ir.maktab58.onlineshop.models.Customer;
import ir.maktab58.onlineshop.models.products.Product;

import java.util.ArrayList;
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

    public void updateOnlineShopProperties() {
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
    }

    public void registerCustomer() {
        try {
            boolean isAdded = addCustomerIfIsNotExisted();
            if (isAdded) {
                System.out.println("New customer is added successfully");
                updateOnlineShopProperties();
                showCustomerMenu(costumers.size() + 1);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private boolean addCustomerIfIsNotExisted() {
        updateOnlineShopProperties();
        Customer newCostumer = getCustomerInformation();
        boolean isExisted = costumers.contains(newCostumer);
        if (isExisted) {
            System.out.println("Sorry this customer " + newCostumer + " is already existed.");
            return false;
        }
        return customerAccess.saveCustomer(newCostumer);
    }

    private Customer getCustomerInformation() {
        System.out.println("Please enter your fullName, username, password, initial balance, nationalCode and birthYear.");
        String inputLine = scanner.nextLine();
        String[] inputTokens = inputLine.split(" ");
        String fullName = inputTokens[0];
        String userName = inputTokens[1];
        String password = inputTokens[2];
        if (fullName.length() == 0 || userName.length() == 0 || password.length() == 0)
            throw new EmptyBufferException("fullName, username and password can not be empty.", 400);
        int initialBalance = Integer.parseInt(inputTokens[3]);
        long nationalCode = Long.parseLong(inputTokens[4]);
        int birthYear = Integer.parseInt(inputTokens[5]);
        return new Customer(costumers.size() + 1, fullName, userName, password, nationalCode, initialBalance, birthYear);
    }

    public void loginAsACustomer() {
        try {
            updateOnlineShopProperties();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter your username.");
            String username = scanner.nextLine().trim();
            System.out.println("Please enter your password.");
            String password = scanner.nextLine().trim();
            for (Customer costumer : costumers) {
                if (costumer.getUsername().equals(username) && costumer.getPassword().equals(password)) {
                    System.out.println("Welcome back " + costumer.getFullName() + ".");
                    showCustomerMenu(costumer.getId());
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showCustomerMenu(int customerId) {
        CartDataBaseAccess cartAccess = new CartDataBaseAccess();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Cart cart = cartAccess.getCart(customerId);
            long totalPrice = calcTotalPrice(cart);
            System.out.println("**********Customer Menu**********");
            System.out.println("1) Add product to cart.");
            System.out.println("2) Delete product from cart.");
            System.out.println("3) Print added items to cart.");
            System.out.println("4) Confirm shopping.");
            System.out.println("5) Deposit your account.");
            System.out.println("6) Back to main menu");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                addProductToCart(customerId);
            } else if (choice.equals("2")) {
                deleteProductFromCart(customerId, cart);
            } else if (choice.equals("3")) {
                printAddedItemsWithPriceToCart(cart, totalPrice);
            } else if (choice.equals("4")) {
                confirmShopping(cart, totalPrice, customerId);
            } else if (choice.equals("5")) {
                depositCustomerBalance(customerId);
            }else if (choice.equals("6")) {
                break;
            } else {
                System.out.println("Invalid input command. Your choice must be an integer between 1 to 6.");
            }
        }
    }

    public void loginAsAnAdmin() {
        boolean isAllowed = admin.isUserAdmin();
        if (isAllowed) {
            System.out.println("Welcome back admin");
            showAdminMenu();
        } else {
            System.out.println("Sorry you are not allowed to access admin menu.");
        }
    }

    private void showAdminMenu() {
        //TODO
    }

    @Override
    public void addProductToCart(int customerId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("There are three types of products: ");
            System.out.println("1) Electronic Devices");
            System.out.println("2) Shoes");
            System.out.println("3) Reading Items");
            System.out.println("4) Back to main menu");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                addElectronicDevicesToCart(customerId);
            } else if (choice.equals("2")) {
                addItemToCart(customerId, ProductType.SHOE.getType());
            } else if (choice.equals("3")) {
                addReadingItemsToCart(customerId);
            } else if (choice.equals("4")) {
                break;
            } else {
                System.out.println("Invalid input command. Your choice must be an integer between 1 to 4.");
            }
        }
    }

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
    }
}
