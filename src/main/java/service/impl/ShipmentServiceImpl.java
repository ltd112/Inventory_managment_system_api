package service.impl;

import dto.ShipmentDTO;
import entity.Shipment;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ShipmentRepository;
import service.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public ShipmentDTO rewriteShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = shipmentRepository.findById(shipmentDTO.getShipmentId()).
                orElseThrow(()-> new ResourceNotFoundException("Shipment", "id", shipmentDTO.getShipmentId()));
        shipment.setShipmentId(shipmentDTO.getShipmentId());
        shipment.setStatus(shipmentDTO.getStatus());
        shipment.setWarehouses(shipmentDTO.getWarehouses());
        shipment.setTrackingNumber(shipmentDTO.getTracking_number());
        shipment.setProducts(shipmentDTO.getProducts());

        shipmentRepository.save(shipment);
        return shipmentDTO;
    }

    @Override
    public ShipmentDTO createShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = mapToEntity(shipmentDTO);
        return mapToDTO(shipmentRepository.save(shipment));
    }

    @Override
    public ShipmentDTO updateShipment(ShipmentDTO shipmentDTO, Long id) {
        Shipment shipment = shipmentRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Shipment", "id", id));

        shipment.setShipmentId(shipmentDTO.getShipmentId());
        shipment.setStatus(shipmentDTO.getStatus());
        shipment.setWarehouses(shipmentDTO.getWarehouses());
        shipment.setTrackingNumber(shipmentDTO.getTracking_number());

        shipmentRepository.save(shipment);

        return mapToDTO(shipment);
    }

    @Override
    public ShipmentDTO getShipmentById(Long id) {
        Shipment shipment = shipmentRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Shipment", "id", id));
        return mapToDTO(shipment);
    }

    @Override
    public void deleteShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Shipment", "id", id));
        shipmentRepository.delete(shipment);

    }

    private ShipmentDTO mapToDTO(Shipment shipment){
        ShipmentDTO shipmentDTO = new ShipmentDTO();

        shipmentDTO.setShipmentId(shipment.getShipmentId());
        shipmentDTO.setStatus(shipment.getStatus());
        shipmentDTO.setWarehouses(shipment.getWarehouses());
        shipmentDTO.setTracking_number(shipment.getTrackingNumber());
        shipmentDTO.setProducts(shipment.getProducts());

        return shipmentDTO;
    }

    private Shipment mapToEntity(ShipmentDTO shipmentDTO){
        Shipment shipment = new Shipment();

        shipment.setShipmentId(shipmentDTO.getShipmentId());
        shipment.setStatus(shipmentDTO.getStatus());
        shipment.setWarehouses(shipmentDTO.getWarehouses());
        shipment.setTrackingNumber(shipmentDTO.getTracking_number());
        shipment.setProducts(shipmentDTO.getProducts());

        return shipment;
    }
}
