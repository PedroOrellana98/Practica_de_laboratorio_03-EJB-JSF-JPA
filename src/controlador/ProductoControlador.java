package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;

import ejb.BodegaEJB;
import ejb.CategoriaEJB;
import ejb.ProductoEJB;
import ejb.StockEJB;
import entidad.Bodega;
import entidad.Categoria;
import entidad.Producto;
import entidad.Stock;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ProductoControlador implements Serializable {
	
	private static final long serialVersionUID=1L;
    @EJB
    private ProductoEJB productoEJB;
    private String nombre;
    private String imagen;
    private String precioCompra;
    private String precioVenta;
    private String iva;
    private String stock;
    private String categoria;
    private List<Categoria> list;
    private List<Bodega> bodegas;
    private List<String> selectedbodegas;
    @EJB
    private BodegaEJB bodegaEJB;

    @EJB
    private CategoriaEJB categoriaEJB;

    private List<Producto> productos;
    private String selectedProducto;
    private String stock_mas;
    private List<String> bodegas_stock;
    private String bodega_inventario;
    private List<Producto> productos_list;
    private boolean disabled=true;
    @EJB
    private StockEJB stockEJB;
    
    public ProductoControlador() {

    }

    @PostConstruct
    public void init(){
        list=categoriaEJB.findAll();
        bodegas= bodegaEJB.findAll();
        productos= productoEJB.findAll();

    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getBodega_inventario() {
        return bodega_inventario;
    }

    public void setBodega_inventario(String bodega_inventario) {
        this.bodega_inventario = bodega_inventario;
        this.disabled=false;
    }


    public List<String> getBodegas_stock() {
        return bodegas_stock;
    }

    public void setBodegas_stock(List<String> bodegas_stock) {
        this.bodegas_stock = bodegas_stock;
    }


    public String getStock_mas() {
        return stock_mas;
    }

    public void setStock_mas(String stock_mas) {
        this.stock_mas = stock_mas;
    }

    public String getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(String selectedProducto) {
        this.selectedProducto = selectedProducto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    public List<String> getSelectedbodegas() {
        return selectedbodegas;
    }

    public void setSelectedbodegas(List<String> selectedbodegas) {
        this.selectedbodegas = selectedbodegas;
    }

    public Categoria[] getList() {
        return list.toArray(new Categoria[0]);
    }

    public void setList(List<Categoria> list) {
        this.list = list;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(String precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Producto> getProductos_list() {
        return consultarInventarioPorBodega();
    }
    
    public void addProducto() {

        char iva_char = iva.charAt(0);

        List<Bodega> bodegas_buscadas = new ArrayList<Bodega>();

        Categoria categoria_buscada = categoriaEJB.buscarCategoriaPorNombre(categoria);
        Producto s1 = new Producto(nombre, imagen, Double.parseDouble(precioCompra), Double.parseDouble(precioVenta), iva_char, Integer.parseInt(stock), categoria_buscada);

        for (String bodega_name : selectedbodegas) {
            String stock_val=this.stock;
            Bodega a1 = bodegaEJB.buscarBodegaPorNombre(bodega_name);
            if (a1 != null) {
                Stock stock = new Stock(Integer.parseInt(stock_val),s1,a1);
                a1.agregarProducto(s1);
                a1.addStock(stock);
                s1.addBodega(a1);
                s1.addStock(stock);
            } else {
                System.out.println("");
            }
        }
        productoEJB.create(s1);
        productos_list = productoEJB.findAll();
    }

    public void setProductos_list(List<Producto> productos_list) {
        this.productos_list = productos_list;
    }
    
    public void aumentarStock(){
        Producto product=productoEJB.buscarPrductoPorNombre(selectedProducto);
        if(product!=null)
            System.out.println("");
        Bodega bodeg= bodegaEJB.buscarBodegaPorNombre(bodegas_stock.get(0));
        if(bodeg!=null)
            System.out.println("");
        Stock stock_actualizar = stockEJB.recuperarStock(product,bodeg);
        stock_actualizar.setStock(Integer.parseInt(stock_mas));
        if(stock_actualizar!=null)
            System.out.println("");
        stockEJB.edit(stock_actualizar);
    }
    
    public List<Producto> consultarInventarioPorBodega(){
        if(bodega_inventario!=null){
            Bodega bodega_to_inventario=bodegaEJB.buscarBodegaPorNombre(bodega_inventario);
            if(bodega_to_inventario!=null) 
                System.out.println("");
            List<Stock> stock_inventario= stockEJB.recuperarStockPorBodega(bodega_to_inventario);
            if(bodega_to_inventario!=null)
                System.out.println("");
            List<Producto> productos_inventario= new ArrayList<Producto>();
            for (Stock stock_inv:stock_inventario
            ) {
                String codigo_prod_inv=stock_inv.getProducto().getNombre();
                Producto producto_inv=productoEJB.buscarPrductoPorNombre(codigo_prod_inv);
                producto_inv.setStock(stock_inv.getStock());
                productos_inventario.add(producto_inv);
            }
            return productos_inventario;
        } else {
            Producto pr = new Producto();
            pr.setNombre("NO EXISTE");
            List<Producto>productos_null= new ArrayList<Producto>();
            productos_null.add(pr);
            return productos_null;

        }
    }
    
    public String getCookie() {
        Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("administrador");
        if(cookie !=null) {
            String cookieValue = cookie.getValue();
            System.out.println(cookieValue + "<------");
            if (cookieValue.isEmpty()) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/public/logIn.xhtml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/public/logIn.xhtml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    
    public void deleteCookie(){
        FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("administrador", "", null);
        Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("administrador");
        if(cookie.getValue().equals("")) System.out.println(); else
            System.out.println();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../Public/logIn.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectBodegas(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../Private/paginaBodega.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
