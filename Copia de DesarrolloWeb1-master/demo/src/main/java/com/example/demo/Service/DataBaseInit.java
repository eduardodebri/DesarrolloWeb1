package com.example.demo.Service;

import com.example.demo.Model.AdicionalesModel;
import com.example.demo.Model.ProductoModel;
import com.example.demo.Repository.AdicionalesRepository;
import com.example.demo.Repository.ProductoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class DataBaseInit {

    @Autowired
    private  ProductoRepository productoRepository;
    @Autowired
    private  AdicionalesRepository adicionalesRepository;
    @Autowired
    private  ProductoService productoService;


    @PostConstruct
    @Transactional
    public void initDatabase() {
        if (productoRepository.count() == 0 && adicionalesRepository.count() == 0) {
            // Agregar Adicionales
            List<AdicionalesModel> adicionales = Arrays.asList(
                    new AdicionalesModel("Salsa Teriyaki", 3000.0),
                    new AdicionalesModel("Salsa Picante", 2500.0),
                    new AdicionalesModel("Aguacate Extra", 4000.0),
                    new AdicionalesModel("Salmón Extra", 7000.0),
                    new AdicionalesModel("Queso Crema", 3500.0),
                    new AdicionalesModel("Tobiko (Hueva de pez volador)", 5000.0),
                    new AdicionalesModel("Wasabi Adicional", 1500.0),
                    new AdicionalesModel("Jengibre Encurtido", 1800.0),
                    new AdicionalesModel("Camarón Extra", 6000.0),
                    new AdicionalesModel("Tempura Crunch", 3000.0)
            );
            adicionalesRepository.saveAll(adicionales);

            // Agregar Productos con algunos adicionales
            List<ProductoModel> productos = Arrays.asList(
                    new ProductoModel("Futomaki Especial", 25000, "Rollos de sushi rellenos de salmón, aguacate y queso crema.", "futomaki.jpeg"),
                    new ProductoModel("California Roll", 20000, "Rollos con cangrejo, pepino y aguacate, cubiertos de ajonjolí.", "california-rolls.jpg"),
                    new ProductoModel("Nigiri Salmón", 18000, "Bocados de arroz con una fina lámina de salmón fresco.", "nigiri_atun.jpg"),
                    new ProductoModel("Nigiri Atún", 19000, "Bocados de arroz con atún fresco de alta calidad.", "nigiri_atun.jpg"),
                    new ProductoModel("Sashimi Mixto", 35000, "Selección de cortes finos de salmón, atún y tilapia.", "sashimi-mixto.jpg"),
                    new ProductoModel("Temaki Camarón", 22000, "Cono de alga relleno de camarón empanizado con salsa spicy mayo.", "temaki-camaron.jpg"),
                    new ProductoModel("Gyozas de Cerdo", 18000, "Empanadillas japonesas rellenas de cerdo y vegetales, al vapor y doradas.", "gyozas-cerdo.jpg"),
                    new ProductoModel("Yakitori Pollo", 17000, "Brochetas de pollo glaseadas con salsa teriyaki.", "yakitori-pollo.jpg"),
                    new ProductoModel("Ramen Tonkotsu", 32000, "Caldo cremoso de cerdo con fideos, huevo marinado y chashu.", "tonkotsu-ramen.jpg"),
                    new ProductoModel("Ramen Shoyu", 31000, "Sopa de fideos en caldo de soya con pollo y vegetales.", "ramen-shoyu.jpg"),
                    new ProductoModel("Ramen Miso", 33000, "Caldo de miso con fideos, carne de cerdo, huevo y cebollín.", "ramen-miso.jpg"),
                    new ProductoModel("Tataki de Atún", 29000, "Láminas de atún selladas con costra de ajonjolí y salsa ponzu.", "tataki-atun.jpg"),
                    new ProductoModel("Tori Katsu", 26000, "Pechuga de pollo empanizada y frita con salsa tonkatsu.", "tori-katsu.jpg"),
                    new ProductoModel("Unagi Don", 38000, "Anguila glaseada sobre arroz con salsa especial.", "unagi-don.jpg"),
                    new ProductoModel("Takoyaki", 20000, "Bolitas de masa rellenas de pulpo, cubiertas con salsa especial y katsuobushi.", "takoyaki.jpg"),
                    new ProductoModel("Ebi Tempura", 27000, "Langostinos tempurizados servidos con salsa tentsuyu.", "ebi-tempura.jpg"),
                    new ProductoModel("Kani Salad", 19000, "Ensalada fresca con cangrejo, pepino, zanahoria y salsa mayo japonesa.", "kani-salad.jpg"),
                    new ProductoModel("Chirashi Sushi", 36000, "Surtido de sashimi servido sobre arroz de sushi.", "chirashi-sushi.jpg"),
                    new ProductoModel("Katsudon", 29000, "Filete de cerdo empanizado con huevo y cebollas sobre arroz.", "katsudon.jpg"),
                    new ProductoModel("Gohan de Vegetales", 17000, "Arroz salteado con vegetales y salsa de soya.", "gohan-vegetales.jpg"),
                    new ProductoModel("Udon con Ternera", 28000, "Fideos udon en caldo con ternera y cebollín.", "udon-ternera.jpg"),
                    new ProductoModel("Okonomiyaki", 26000, "Tortilla japonesa con repollo, panceta y toppings variados.", "okonomiyaki.jpg"),
                    new ProductoModel("Sopa de Miso", 12000, "Caldo de miso con tofu, alga wakame y cebollín.", "sopa-miso.jpg"),
                    new ProductoModel("Donburi de Pollo", 25000, "Pollo salteado con vegetales sobre arroz.", "donburi-pollo.jpg"),
                    new ProductoModel("Bento Box", 30000, "Combinación de sushi, arroz, tempura y proteínas.", "bento-box.jpeg")

            );
            productoRepository.saveAll(productos);


            List<ProductoModel> productosGuardados = productoRepository.findAll();
            List<AdicionalesModel> adicionalesGuardados = adicionalesRepository.findAll();

            Random random = new Random();
            for (ProductoModel producto : productosGuardados) {
                // Barajar los adicionales aleatoriamente
                Collections.shuffle(adicionalesGuardados, random);

                // Seleccionar los primeros 3 adicionales aleatorios
                List<AdicionalesModel> adicionalesRandom = adicionalesGuardados.subList(0, 3);

                // Usar la función agregarAdicionalAProducto para agregar cada adicional
                for (AdicionalesModel adicional : adicionalesRandom) {
                    productoService.agregarAdicionalAProducto(producto.getId(), adicional.getId());
                }
            }
            






            System.out.println("🚀 Base de datos inicializada correctamente con productos y adicionales.");
        } else {
            System.out.println("⚠ La base de datos ya contiene datos, no se inicializó nuevamente.");
        }
    }

}