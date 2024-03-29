package com.enoca.repository;

import com.enoca.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
     @Query("SELECT c FROM CartItem c WHERE c.cart.id=?1 AND c.order IS NULL")
     List<CartItem> getByCartIdAndOrderNull(Long cartId);

}
