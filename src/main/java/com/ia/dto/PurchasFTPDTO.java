package com.ia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"Id_Pedido", "Monto_Compra","DNI_Cliente", "Nombre_Cliente", "mail_Cliente","Id_Producto","Descripcion_Producto" })
public class PurchasFTPDTO {
    private int IdPedido;
    private float MontoCompra;
    private String DNICliente;
    private String NombreCliente;
    private String mailCliente;
    private String IdProducto;
    private String DescripcionProducto;

    public PurchasFTPDTO(int idPedido, float montoCompra, String DNICliente, String nombreCliente, String mailCliente, String idProducto, String descripcionProducto) {
        IdPedido = idPedido;
        MontoCompra = montoCompra;
        this.DNICliente = DNICliente;
        NombreCliente = nombreCliente;
        this.mailCliente = mailCliente;
        IdProducto = idProducto;
        DescripcionProducto = descripcionProducto;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int idPedido) {
        IdPedido = idPedido;
    }

    public float getMontoCompra() {
        return MontoCompra;
    }

    public void setMontoCompra(float montoCompra) {
        MontoCompra = montoCompra;
    }

    public String getDNICliente() {
        return DNICliente;
    }

    public void setDNICliente(String DNICliente) {
        this.DNICliente = DNICliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getMailCliente() {
        return mailCliente;
    }

    public void setMailCliente(String mailCliente) {
        this.mailCliente = mailCliente;
    }

    public String getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(String idProducto) {
        IdProducto = idProducto;
    }

    public String getDescripcionProducto() {
        return DescripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        DescripcionProducto = descripcionProducto;
    }
}
