package com.umg.edu.gt.progra2.HelloWorld.Controller;
import com.umg.edu.gt.progra2.HelloWorld.Dto.TipoCambio;
import com.umg.edu.gt.progra2.HelloWorld.service.TipoCambioStringService;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoCambioControllerString {
    
    @Autowired
    private TipoCambioStringService tipoCambioStringService;

    @GetMapping("/tipoCambioDia")
    public ResponseEntity<Object> obtenerTipoCambioString() {
        String tipoCambio = tipoCambioStringService.obtenerTipoCambioString();

        // Verificar si la respuesta contiene errores
        if (tipoCambio.contains("Error")) {
            return ResponseEntity.status(500).body("Error: " + tipoCambio);
        }
                try {
            // Convertir el XML a JSON
            JSONObject xmlJSONObj = XML.toJSONObject(tipoCambio);

            // datos específicos del JSON
            JSONObject varDolar = xmlJSONObj.getJSONObject("soap:Envelope")
                    .getJSONObject("soap:Body")
                    .getJSONObject("TipoCambioStringResponse")
                    .getJSONObject("TipoCambioStringResult")
                    .getJSONObject("CambioDolar")
                    .getJSONObject("VarDolar");

            // traer los valores que te interesan
            String fecha = varDolar.getString("fecha");
            double referencia = varDolar.getDouble("referencia");

            // se creo la instancia del TipoCambio
            TipoCambio tipoCambioDTO = new TipoCambio(fecha, referencia);

            // Devolver la respuesta con el TipoCambio
            return ResponseEntity.ok(tipoCambioDTO);
        } catch (JSONException e) {
            return ResponseEntity.status(500).body("Error al procesar el XML.");
        }
    }
}
