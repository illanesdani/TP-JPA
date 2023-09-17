package entidades;

import enumeraciones.Estado;
import enumeraciones.TipoDeEnvio;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name="pedido")
public class Pedido extends BaseEntidad{
    private Estado estado;
@Temporal(TemporalType.DATE)
    private Date fecha;
    private TipoDeEnvio tipoEnvio;
    private double total;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name="factura-id")
    private Factura factura;

    @OneToMany(cascade =CascadeType.ALL,orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name="pedido-id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public void agregarDetallePedido (DetallePedido detallePed){
       detallePedidos.add(detallePed);
    }


    //no quería hacer esto pero no me anduvieron los getters y setters de lombock y no quedó otra
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoDeEnvio getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoDeEnvio tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public void mostrarDetalles() {
        System.out.println("Detalles de " + Pedido.this);
        for (DetallePedido detalle : detallePedidos) {
            System.out.println("Cantidad: " + detalle.getCantidad() + ", Subtotal: " + detalle.getSubtotal());
        }

    }


}
