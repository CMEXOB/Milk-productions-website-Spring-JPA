package com.factoryproduct.service;

import com.factoryproduct.entity.Factory;
import com.factoryproduct.entity.Price;
import com.factoryproduct.entity.Product;
import com.factoryproduct.repository.FactoryRepository;
import com.factoryproduct.repository.PriceRepository;
import com.factoryproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PriceService {

    private PriceRepository priceRepository;
    private FactoryRepository factoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository, FactoryRepository factoryRepository,
                        ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.factoryRepository = factoryRepository;
        this.productRepository = productRepository;
    }

    public List<Price> getPrices() {
        return priceRepository.findAll();
    }
    public Price getPriceById(Long priceId) {
        return priceRepository.findById(priceId).get();
    }

    public void addPrice(Factory factory, Product product, Date date,
                         Double purchasePrice, Double sellingPrice,
                         String informantFullName, String informantPhoneNumber, String informantJobTitle) {
        Price price = new Price();

        price.setFactory(factory);
        price.setProduct(product);
        price.setDate(date);
        price.setPurchasePrice(purchasePrice);
        price.setSellingPrice(sellingPrice);
        price.setInformantFullName(informantFullName);
        price.setInformantPhoneNumber(informantPhoneNumber);
        price.setInformantJobTitle(informantJobTitle);

        priceRepository.save(price);
    }

    public void deletePrice(Long priceId) {
        priceRepository.deleteById(priceId);
    }

    public void updatePrice(Long priceId, Long factoryId, Long productId, Date date, Double purchasePrice,
                            Double sellingPrice, String fullName,
                            String informantPhoneNumber, String informantJobTitle) {
        Price updatedPrice = priceRepository.findById(priceId).get();
        Factory factory = factoryRepository.findById(factoryId).get();
        Product product = productRepository.findById(productId).get();

        updatedPrice.setFactory(factory);
        updatedPrice.setProduct(product);
        updatedPrice.setDate(date);
        updatedPrice.setPurchasePrice(purchasePrice);
        updatedPrice.setSellingPrice(sellingPrice);
        updatedPrice.setInformantFullName(fullName);
        updatedPrice.setInformantPhoneNumber(informantPhoneNumber);
        updatedPrice.setInformantJobTitle(informantJobTitle);
        priceRepository.save(updatedPrice);
    }

    public List<Price> getPriceByProductName(Date dateAfter, Date dateBefore, String productName) {
        return priceRepository.findPriceByProductName(dateAfter, dateBefore, productName);
    }

    public List<Price> getPriceByFactoryName(Date date, String factoryName) {
        return priceRepository.findPriceByFactoryName(date, factoryName);
    }

    public List<List<Object>> getPriceByFactoryRegionName(Date date, String regionName) {
        return priceRepository.findAvgPriceByFactoryRegion(date, regionName);
    }
}
