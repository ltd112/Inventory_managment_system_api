package service;

import dto.ShipmentDTO;

public interface ShipmentService {
    ShipmentDTO rewriteShipment(ShipmentDTO shipmentDTO); //put

    ShipmentDTO createShipment(ShipmentDTO shipmentDTO);

    ShipmentDTO updateShipment(ShipmentDTO shipmentDTO, Long id);

    ShipmentDTO getShipmentById(Long id);

    void deleteShipment(Long id);
}
