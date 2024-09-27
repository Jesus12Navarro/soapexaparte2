package com.umg.edu.gt.progra2.HelloWorld;

import org.junit.jupiter.api.Test;
import com.umg.edu.gt.progra2.HelloWorld.service.TipoCambioStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSring {
    @Autowired
    private TipoCambioStringService TipoCambioStringService;
    
    @Test
    public void testobtenerTipoCambioString(){
        System.out.println("init");
        TipoCambioStringService TipoCambioStringService1 = TipoCambioStringService;
    }            
}
