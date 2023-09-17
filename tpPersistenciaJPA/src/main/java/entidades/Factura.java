package entidades;


import enumeraciones.FormaDePago;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name="factura")
public class Factura extends BaseEntidad{
    private int numero;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    private double descuento;
    private FormaDePago formaPago;
    private double total;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public FormaDePago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaDePago formaPago) {
        this.formaPago = formaPago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
