package com.java.sbz.services;

import com.java.sbz.dtos.CartItemDTO;
import com.java.sbz.models.*;
import com.java.sbz.repository.ArticleRepository;
import com.java.sbz.repository.OrderRespository;
import com.java.sbz.repository.SaleEventRepository;
import com.java.sbz.repository.UserRepository;
import com.java.sbz.util.ServiceReturn;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sirko on 9/7/17.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRespository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SaleEventRepository saleEventRepository;

    @Autowired
    private KieContainer kieContainer;

    public ServiceReturn getOrderFromCart(String userId,List<CartItemDTO> data){
        try{

            Receipt order = new Receipt();

            //aduser
            User u=userRepository.findOneByUsername(userId);
            if(u==null) return new ServiceReturn(false,"user not found");

            order.setCustomer(u);

            //add items
            List<Item> items=new ArrayList<Item>();
            for(CartItemDTO cartItem:data){

                Article a=articleRepository.findOne(cartItem.getId());
                if(a==null) return new ServiceReturn(false,"article not found");

                items.add(new Item(cartItem,a));
            }

            order.setItems(items);
            order.setDate(new Date());

            //ispali pravila
            KieSession kieSession = kieContainer.newKieSession("session");
            kieSession.insert(order);

            //add sales to session
            List<SaleEvent> sales=saleEventRepository.findAll();
            for(SaleEvent s : sales) kieSession.insert(s);

            kieSession.getAgenda().getAgendaGroup("order_discounts").setFocus();
            kieSession.fireAllRules();

            //orderRepository.save(order);

            return new ServiceReturn(true,null,order);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn addOrder(Receipt order){
        try{




            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn getUserOrders(String username){
        try{
            User user=userRepository.findOneByUsername(username);
            List<Receipt> data = orderRepository.findAllByCustomer(user);

            return new ServiceReturn(true,null,data);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }


}
