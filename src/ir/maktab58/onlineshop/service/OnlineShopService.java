package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.enumation.ElectronicDevicesTypes;
import ir.maktab58.onlineshop.enumation.ReadingItemsTypes;
import ir.maktab58.onlineshop.exceptions.OnlineShopExceptions;
import ir.maktab58.onlineshop.models.Cart;
import ir.maktab58.onlineshop.models.Customer;
import ir.maktab58.onlineshop.models.products.Product;
import ir.maktab58.onlineshop.service.validator.NationalCodeValidator;
import ir.maktab58.onlineshop.service.validator.UserAndPassValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        boolean isNationalCodeValid = NationalCodeValidator.getSingletonInstance().isNationalCodeValid(nationalCode);
        boolean isUserAndPassValid = UserAndPassValidator.getSingletonInstance().isUserAndPassValid(username, password);
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
                .message("Invalid type of electronic-device Item: " + typeOfDevice)
                .errorCode(400).build();
    }

    @Override
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

    @Override
    public void depositCustomerBalance(long charge, int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        customer.setInitialBalance(customer.getInitialBalance() + charge);
        customerService.updateCustomerBalance(customer);
    }

    public List<Cart> getCustomerCarts(int customerId) {
        return cartService.getCustomerCarts(customerId);
    }

    @Override
    public long calculateTotalPrice(List<Cart> customerCarts) {
        return customerCarts.stream()
                .map(cart -> cart.getQuantity() * cart.getProduct().getPrice())
                .reduce(0l, Long::sum);
    }

    @Override
    public void deleteAnItemFromCart(List<Cart> customerCarts, int cartId, int customerId) {
        Optional<Cart> foundedCart = customerCarts.stream().filter(cart -> cartId == cart.getId()).findFirst();
        if (foundedCart.isEmpty())
            throw OnlineShopExceptions.builder()
                    .message(cartId + " is not existed.")
                    .errorCode(400).build();
        Cart cart = foundedCart.get();
        cartService.deleteCart(cart, customerId);
    }

    @Override
    public void confirmShopping(int customerId) {
        List<Cart> customerCarts = getCustomerCarts(customerId);
        long totalPrice = calculateTotalPrice(customerCarts);
        Customer customer = customerService.getCustomerById(customerId);
        if (customer.getInitialBalance() < totalPrice) {
            throw OnlineShopExceptions.builder()
                    .message("You should deposit your balance: +" + (customer.getInitialBalance() - totalPrice))
                    .errorCode(400).build();
        }
        customer.setInitialBalance(customer.getInitialBalance() - totalPrice);
        customerService.updateCustomerBalance(customer);
        customerCarts.forEach(cart -> {
            Product product = cart.getProduct();
            product.setCount(product.getCount() - cart.getQuantity());
            productService.updateProductCount(product);
            cartService.deleteCart(cart, customerId);
        });
    }

    @Override
    public List<Product> getNotEnoughProducts(int customerId) {
        List<Cart> customerCarts = getCustomerCarts(customerId);
        List<Product> products = new ArrayList<>();
        customerCarts.forEach(cart -> {
            if (cart.getProduct().getCount() < cart.getQuantity()) {
                products.add(cart.getProduct());
                cartService.deleteCart(cart, customerId);
            }
        });
        return products;
    }
}
