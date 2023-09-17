package entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name="Cliente")
public class Cliente extends BaseEntidad{

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    @JoinColumn(name="persona_id")
    @Builder.Default
    private List<Domicilio> domicilios = new ArrayList();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    @JoinColumn(name="persona_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList();



    public void agregarDomicilio (Domicilio domi){
        domicilios.add(domi);
    }

    public void agregarPedido (Pedido ped){
        pedidos.add(ped);
    }


    public void mostrarDomicilios (){
        System.out.println("Los domicilios de  " + nombre + " " + apellido + "son: ");
        for (Domicilio domi : domicilios){
            System.out.println("Calle " + domi.getCalle() + ", Número: " + domi.getNumero());
        }
    }
    public void mostrarPedidos() {
        System.out.println("Los domicilios de " + nombre + " " + apellido + " son:");
        for (Pedido pedido : pedidos) {
            System.out.println("Estado: " + pedido.getEstado());
            System.out.println("Tipo de Envio: " + pedido.getTipoEnvio());
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Total: "+ pedido.getTotal());
            System.out.println("Factura: "+ pedido.getFactura());
            pedido.mostrarDetalles();
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Domicilio> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

