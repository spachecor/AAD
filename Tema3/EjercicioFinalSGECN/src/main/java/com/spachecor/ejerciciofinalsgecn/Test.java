package com.spachecor.ejerciciofinalsgecn;

import com.spachecor.ejerciciofinalsgecn.model.service.repository.ServiceUtil;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        /*Session session = ServiceUtil.getSession();
        session.beginTransaction();
        session.createNativeQuery("delete from estudiante_curso").executeUpdate();
        session.getTransaction().commit();*/
        Session session = ServiceUtil.getSession();
        session.beginTransaction();
        Object o = session.createNativeQuery("select * from estudiante_curso").getResultList();
        System.out.println("");
        session.getTransaction().commit();
    }
}
