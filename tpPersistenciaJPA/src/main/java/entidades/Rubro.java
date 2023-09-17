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
@Getter
@Setter
@Table(name="rubro")
public class Rubro extends BaseEntidad {
    private String denominación;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @JoinColumn(name="rubro-id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProductos (Producto prod){
        productos.add(prod);
    }

    public void mostrarProductos() {
        System.out.println("Los productos de este rubro son: ");
        for (Producto producto : productos) {
            System.out.println("Denominacion: " + producto.getDenominacion() + ", Receta: " + producto.getReceta()
                    + ", Unidad Medida: " + producto.getUnidadMedida() + ", Precio Compra: " + producto.getPrecioCompra()
                    + ", Precio Venta: " + producto.getPrecioVenta() + ", Stock Actual: " + producto.getStockActual()
                    + ", Stock Min: " + producto.getStockMinimo() + ", Tiempo Estimado Cocina: " + producto.getTiempoEstimadoCocina()
                    + ", Tipo: " + producto.getTipo());
        }
    }

    public String getDenominación() {
        return denominación;
    }

    public void setDenominación(String denominación) {
        this.denominación = denominación;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
