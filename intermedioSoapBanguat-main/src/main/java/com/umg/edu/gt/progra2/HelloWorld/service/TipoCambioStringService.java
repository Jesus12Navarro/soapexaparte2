/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.edu.gt.progra2.HelloWorld.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;

@Service
public class TipoCambioStringService {
    
    public String obtenerTipoCambioString() {
        String soapEndpoint = "http://www.banguat.gob.gt/variables/ws/TipoCambioDiaString.asmx?WSDL";
        String soapAction = "http://www.banguat.gob.gt/variables/ws/TipoCambioDiaString";

        String soapRequest =
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                        "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                        "<soap:Body>" +
                        "<TipoCambioDia xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />" +
                        "</soap:Body>" +
                        "</soap:Envelope>";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", soapAction);

        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);
        
        try {
            return restTemplate.exchange(soapEndpoint, HttpMethod.POST, request, String.class).getBody();
        } catch (RestClientException e) {
            return "Error al obtener el tipo de cambio: " + e.getMessage();
        }
    }    
}
