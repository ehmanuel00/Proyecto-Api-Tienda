package com.proyecto.api.persistence;

import com.proyecto.api.domain.Purchase;
import com.proyecto.api.domain.repository.PurchaseRepository;
import com.proyecto.api.persistence.crud.CompraCrudRepository;
import com.proyecto.api.persistence.entity.Compra;
import com.proyecto.api.persistence.entity.Producto;
import com.proyecto.api.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return mapper.toPurchases(compras);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId).map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
