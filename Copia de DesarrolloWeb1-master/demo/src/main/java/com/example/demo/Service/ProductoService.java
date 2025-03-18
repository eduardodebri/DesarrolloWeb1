package com.example.demo.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.demo.Model.AdicionalesModel;
import com.example.demo.Repository.AdicionalesRepository;
import jakarta.annotation.PostConstruct;

import com.example.demo.Model.ProductoModel;
import com.example.demo.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements ProductosServiceInterface {

    private final ProductoRepository productoRepository;
    private final AdicionalesRepository adicionalesRepository;

    public ProductoService(ProductoRepository productoRepository, AdicionalesRepository adicionalesRepository) {
        this.productoRepository = productoRepository;
        this.adicionalesRepository = adicionalesRepository;
    }

    public List<ProductoModel> obtenerProductos() {
        return productoRepository.findAll();
    }

    public void agregarProducto(ProductoModel producto) {
        productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public ProductoModel obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void agregarAdicionalAProducto(Long productoId, Long adicionalId) {
        Optional<ProductoModel> productoOpt = productoRepository.findById(productoId);
        Optional<AdicionalesModel> adicionalOpt = adicionalesRepository.findById(adicionalId);

        if (productoOpt.isPresent() && adicionalOpt.isPresent()) {
            ProductoModel producto = productoOpt.get();
            AdicionalesModel adicional = adicionalOpt.get();

            // Evitar duplicados en la lista de adicionales
            if (!producto.getAdicionales().contains(adicional)) {
                producto.getAdicionales().add(adicional);
                productoRepository.save(producto);
            }
        } else {
            throw new RuntimeException("Producto o Adicional no encontrado");
        }
    }



    public void removerAdicionalDeProducto(Long productoId, Long adicionalId) {
        Optional<ProductoModel> productoOpt = productoRepository.findById(productoId);
        Optional<AdicionalesModel> adicionalOpt = adicionalesRepository.findById(adicionalId);

        if (productoOpt.isPresent() && adicionalOpt.isPresent()) {
            ProductoModel producto = productoOpt.get();
            AdicionalesModel adicional = adicionalOpt.get();

            // Remover el adicional de la lista del producto
            producto.getAdicionales().remove(adicional);
            productoRepository.save(producto);
        } else {
            throw new RuntimeException("Producto o Adicional no encontrado");
        }
    }




        // Método para eliminar un adicional de todos los productos antes de eliminarlo
        public void eliminarAdicionalDeTodosLosProductos(Long adicionalId) {
            Optional<AdicionalesModel> adicionalOpt = adicionalesRepository.findById(adicionalId);
            if (adicionalOpt.isPresent()) {
                AdicionalesModel adicional = adicionalOpt.get();

                // Obtener todos los productos
                List<ProductoModel> productos = productoRepository.findAll();

                for (ProductoModel producto : productos) {
                    if (producto.getAdicionales().contains(adicional)) {
                        producto.getAdicionales().remove(adicional);
                        productoRepository.save(producto); // Guardar cambios en el producto
                    }
                }
            }
        }

    @PostConstruct
    public void inicializarProductos() {

            List<ProductoModel> productos = Arrays.asList(
                    new ProductoModel("Futomaki Especial", 25000, "Rollos de sushi rellenos de salmón, aguacate y queso crema.", "futomaki.jpeg"),
                    new ProductoModel("California Roll", 20000, "Rollos con cangrejo, pepino y aguacate, cubiertos de ajonjolí.", "futomaki.jpeg"),
                    new ProductoModel("Nigiri Salmón", 18000, "Bocados de arroz con una fina lámina de salmón fresco.", "futomaki.jpeg"),
                    new ProductoModel("Nigiri Atún", 19000, "Bocados de arroz con atún fresco de alta calidad.", "futomaki.jpeg"),
                    new ProductoModel("Sashimi Mixto", 35000, "Selección de cortes finos de salmón, atún y tilapia.", "futomaki.jpeg"),
                    new ProductoModel("Temaki Camarón", 22000, "Cono de alga relleno de camarón empanizado con salsa spicy mayo.", "futomaki.jpeg"),
                    new ProductoModel("Gyozas de Cerdo", 18000, "Empanadillas japonesas rellenas de cerdo y vegetales, al vapor y doradas.", "futomaki.jpeg"),
                    new ProductoModel("Yakitori Pollo", 17000, "Brochetas de pollo glaseadas con salsa teriyaki.", "futomaki.jpeg"),
                    new ProductoModel("Ramen Tonkotsu", 32000, "Caldo cremoso de cerdo con fideos, huevo marinado y chashu.", "futomaki.jpeg"),
                    new ProductoModel("Ramen Shoyu", 31000, "Sopa de fideos en caldo de soya con pollo y vegetales.", "futomaki.jpeg"),
                    new ProductoModel("Ramen Miso", 33000, "Caldo de miso con fideos, carne de cerdo, huevo y cebollín.", "futomaki.jpeg"),
                    new ProductoModel("Tataki de Atún", 29000, "Láminas de atún selladas con costra de ajonjolí y salsa ponzu.", "futomaki.jpeg"),
                    new ProductoModel("Tori Katsu", 26000, "Pechuga de pollo empanizada y frita con salsa tonkatsu.", "futomaki.jpeg"),
                    new ProductoModel("Unagi Don", 38000, "Anguila glaseada sobre arroz con salsa especial.", "futomaki.jpeg"),
                    new ProductoModel("Takoyaki", 20000, "Bolitas de masa rellenas de pulpo, cubiertas con salsa especial y katsuobushi.", "futomaki.jpeg"),
                    new ProductoModel("Ebi Tempura", 27000, "Langostinos tempurizados servidos con salsa tentsuyu.", "futomaki.jpeg"),
                    new ProductoModel("Kani Salad", 19000, "Ensalada fresca con cangrejo, pepino, zanahoria y salsa mayo japonesa.", "futomaki.jpeg"),
                    new ProductoModel("Chirashi Sushi", 36000, "Surtido de sashimi servido sobre arroz de sushi.", "futomaki.jpeg"),
                    new ProductoModel("Katsudon", 29000, "Filete de cerdo empanizado con huevo y cebollas sobre arroz.", "futomaki.jpeg"),
                    new ProductoModel("Gohan de Vegetales", 17000, "Arroz salteado con vegetales y salsa de soya.", "futomaki.jpeg"),
                    new ProductoModel("Udon con Ternera", 28000, "Fideos udon en caldo con ternera y cebollín.", "futomaki.jpeg"),
                    new ProductoModel("Okonomiyaki", 26000, "Tortilla japonesa con repollo, panceta y toppings variados.", "futomaki.jpeg"),
                    new ProductoModel("Sopa de Miso", 12000, "Caldo de miso con tofu, alga wakame y cebollín.", "futomaki.jpeg"),
                    new ProductoModel("Donburi de Pollo", 25000, "Pollo salteado con vegetales sobre arroz.", "futomaki.jpeg"),
                    new ProductoModel("Bento Box", 30000, "Combinación de sushi, arroz, tempura y proteínas.", "futomaki.jpeg"),
                    new ProductoModel("Tonkatsu", 27000, "Chuleta de cerdo empanizada y frita con salsa especial.", "futomaki.jpeg"),
                    new ProductoModel("Bubble Tea", 15000, "Té con leche y perlas de tapioca.", "futomaki.jpeg"),
                    new ProductoModel("Sake Japonés", 35000, "Licor de arroz japonés tradicional.", "futomaki.jpeg"),
                    new ProductoModel("Cerveza Asahi", 18000, "Cerveza japonesa ligera y refrescante.", "futomaki.jpeg"),
                    new ProductoModel("Té Verde Sencha", 12000, "Infusión japonesa de hojas de té verde.", "futomaki.jpeg"),
                    new ProductoModel("Té de Jengibre", 13000, "Infusión caliente de jengibre y miel.", "futomaki.jpeg"),
                    new ProductoModel("Ramune Soda", 14000, "Refresco japonés con botella de canica.", "futomaki.jpeg")
            );

            productoRepository.saveAll(productos); // Guardar en la BD

    }


    }






