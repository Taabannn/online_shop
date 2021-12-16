package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.dao.ProductDao;
import ir.maktab58.onlineshop.models.Cart;
import ir.maktab58.onlineshop.models.products.Product;
import ir.maktab58.onlineshop.models.products.Shoe;
import ir.maktab58.onlineshop.models.products.electronicdevices.ElectronicDevices;
import ir.maktab58.onlineshop.models.products.electronicdevices.Radio;
import ir.maktab58.onlineshop.models.products.electronicdevices.Television;
import ir.maktab58.onlineshop.models.products.readingItems.Book;
import ir.maktab58.onlineshop.models.products.readingItems.Magazine;
import ir.maktab58.onlineshop.models.products.readingItems.ReadingItems;

import java.util.List;

/**
 * @author Taban Soleymani
 */
public class ProductService {
    private  final ProductDao<ReadingItems> readingItemsDao = new ProductDao<>();
    private  final ProductDao<Shoe> shoeProductDao = new ProductDao<>();
    private final ProductDao<ElectronicDevices> eDeviceDao = new ProductDao<>();
    private final ProductDao<Product> productDao = new ProductDao<>();

    public List<Product> getMagazines() {
        return readingItemsDao.getAllProductsFromThisType(Magazine.class);
    }

    public List<Product> getBooks() {
        return readingItemsDao.getAllProductsFromThisType(Book.class);
    }

    public List<Product> getShoes() {
        return shoeProductDao.getAllProductsFromThisType(Shoe.class);
    }

    public List<Product> getTVs() {
        return eDeviceDao.getAllProductsFromThisType(Television.class);
    }

    public List<Product> getRadios() {
        return eDeviceDao.getAllProductsFromThisType(Radio.class);
    }
}
