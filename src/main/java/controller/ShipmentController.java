package controller;

import dto.ShipmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ShipmentService;

@RestController
@RequestMapping("/api/v1/shipment")
public class ShipmentController {
    private final Logger log = LoggerFactory.getLogger(ShipmentController.class);

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/{shipmentId}")
    public ResponseEntity<ShipmentDTO> getShipmentById(@PathVariable(name = "shipmentId") Long id){
        return ResponseEntity.ok(shipmentService.getShipmentById(id));
    }

    @PutMapping
    public ResponseEntity<ShipmentDTO> rewriteShipment(@RequestBody ShipmentDTO shipmentDTO){
        ShipmentDTO rewriteShipment = shipmentService.rewriteShipment(shipmentDTO);
        return new ResponseEntity<>(rewriteShipment, org.springframework.http.HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShipmentDTO> createShipment(@RequestBody ShipmentDTO shipmentDTO){
        return new ResponseEntity<>(shipmentService.createShipment(shipmentDTO), org.springframework.http.HttpStatus.CREATED);
    }

    @PatchMapping("/{shipmentId}")
    public ResponseEntity<ShipmentDTO> updateShipment(@RequestBody ShipmentDTO shipmentDTO, @PathVariable(name = "shipmentId") Long id){
        return new ResponseEntity<>(shipmentService.updateShipment(shipmentDTO, id), org.springframework.http.HttpStatus.OK);
    }

    @DeleteMapping("/{shipmentId}")
    public ResponseEntity<String> deleteShipment(@PathVariable(name = "shipmentId") Long id){
        shipmentService.deleteShipment(id);
        return new ResponseEntity<>("deleted complete", org.springframework.http.HttpStatus.OK);
    }
}
