package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.component.UIOutput;

import ejb.BodegaEJB;
import ejb.CategoriaEJB;
import ejb.ProductoEJB;
import entidad.Bodega;
import entidad.Categoria;
import entidad.Producto;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class CatalogoControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
    private ProductoEJB productoEJB;
    @EJB
    private CategoriaEJB categoriaEJB;
    @EJB
    private BodegaEJB bodegaEJB;
    
    private Map<String, String> mapaCodigoNombreProductos;
    private List<Producto> productosList;
    private List<Producto> filtrado;
    
    private Map<String, String> mapaCodigoNombreProducto;
    
    private Map<Integer, Producto> mapaCodigoProducto;
    private Producto producto;
	  
    private List<Bodega> bodegaList;
    private String categoriaSeleccionada;
    private String bodegaSeleccionada;

    @PostConstruct
    public void init() {
        mapaCodigoNombreProducto = new HashMap<>();
        mapaCodigoProducto = new HashMap<>();
        productosList = productoEJB.findAll();
        productosList.forEach(e->{mapaCodigoProducto.put(e.getCodigo(), e);});
        mapaCodigoNombreProducto = new TreeMap<>();
        mapaCodigoNombreProductos = new HashMap<>();
        producto = new Producto();
    }
    
    public Producto getProducto(){return this.producto; }
    public void setProducto(Producto producto){this.producto = producto;}

    public CatalogoControlador(){
        filtrado = new ArrayList<>();
    }

    public void filtrarProductos(AjaxBehaviorEvent event){
        mapaCodigoNombreProductos = buscarProducto((String)
        		((UIOutput) event.getSource()).getValue());
    }

    public void abrirProducto(String param){
        producto = mapaCodigoProducto.get(Integer.parseInt(param));
    }

    public List<String> getCategorias(){
        return categoriaEJB.findAll().parallelStream().map(Categoria::getNombre).collect(Collectors.toList());
    }

    public List<String> getBodegas(){
        bodegaList = bodegaEJB.findAll();
        return bodegaList.parallelStream().map(Bodega::getNombre).collect(Collectors.toList());
    }

    public Map<String, String> getProductos() {
        return mapaCodigoNombreProductos;
    }

    public void cargarProductosPorCategoria(){
        mapaCodigoNombreProductos = productoEJB.getProductosPorCategoria(categoriaEJB.getCategoryByName(categoriaSeleccionada));
    }

    public String getCategoriaSeleccionada(){
        return this.categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(String categoriaSeleccionada){
        this.categoriaSeleccionada = categoriaSeleccionada;
    }
    
    public void cargarProductosPorBodega(){
        AtomicInteger atomicInteger= new AtomicInteger();
        Optional<Bodega> bodega = bodegaList.stream().filter(e -> e.getNombre().equals(bodegaSeleccionada)).findFirst();
        bodega.ifPresent(value -> {
            atomicInteger.set(value.getCodigo());});
        int codigoBodega = atomicInteger.get();
        List<Integer> idProductos = productoEJB.getProductosPorBodega(codigoBodega);
        if(!idProductos.isEmpty()){
            idProductos.forEach(
                    e->{
                        producto = productoEJB.find(e);
                        mapaCodigoNombreProductos.put(String.valueOf(producto.getCodigo()), producto.getNombre());
                    }
            );
            System.out.println(mapaCodigoNombreProductos);
        }
    }

    public void setBodegaSeleccionada(String bodegaSeleccionada){
        this.bodegaSeleccionada = bodegaSeleccionada;
    }

    public String getBodegaSeleccionada(){
        return this.bodegaSeleccionada;
    }
    
    private Map<String, String> buscarProducto(String productoNombre){
        mapaCodigoNombreProducto = new TreeMap<>();
        filtrado = productosList.stream().filter(value -> value.getNombre().toUpperCase().contains(productoNombre.toUpperCase())).collect(Collectors.toList());
        filtrado.forEach(e ->{mapaCodigoNombreProducto.put(String.valueOf(e.getCodigo()), e.getNombre()); });
        return mapaCodigoNombreProducto.isEmpty() ? new HashMap<>() : mapaCodigoNombreProducto;
    }

    public void redirect(){
        System.out.println("LEO");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../Public/logIn.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
