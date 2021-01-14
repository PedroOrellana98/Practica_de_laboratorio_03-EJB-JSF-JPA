package ejb;

import java.util.*;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

import entidad.Categoria;
import entidad.Producto;

import static java.lang.String.valueOf;

@Stateless
public class ProductoEJB extends AbstractFacade<Producto>{

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
    private EntityManager entityManager;
	public ProductoEJB() {
		super(Producto.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	public Producto buscarProducto(String nombre){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
        Root<Producto> usuarioRoot=criteriaQuery.from(Producto.class);
        Predicate predicate = criteriaBuilder.equal(usuarioRoot.get("nombre"),nombre);
        criteriaQuery.select(usuarioRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public Producto buscarPrductoPorNombre(String nombre){
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> criteriaQuery= criteriaBuilder.createQuery(Producto.class);
        Root<Producto> categoriaRoot= criteriaQuery.from(Producto.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("nombre"),nombre);
        criteriaQuery.select(categoriaRoot).where(predicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
    public Producto buscarProductoPorCodigo(String id){
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> criteriaQuery= criteriaBuilder.createQuery(Producto.class);
        Root<Producto> categoriaRoot= criteriaQuery.from(Producto.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("codigo"),id);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public Map<String, String> getProductosPorCategoria(Categoria categoria){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> productoCriteriaQuery = criteriaBuilder.createQuery(Producto.class);
        Root<Producto> productoRoot = productoCriteriaQuery.from(Producto.class);
        productoCriteriaQuery.select(productoRoot)
                .where(
                        criteriaBuilder.equal(productoRoot.get("categoria"), categoria));

         return entityManager.createQuery(productoCriteriaQuery).getResultList()
                 .parallelStream()
                 .collect(Collectors.toMap(Producto::getCodigo, Producto::getNombre)).entrySet()
                 .parallelStream()
                 .collect(Collectors.toMap(entry -> valueOf(entry.getKey()), Map.Entry::getValue));
    }
    private List<Integer> codigoProductos;
    public List<Integer> getProductosPorBodega(int codigoBodega){
        codigoProductos = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT productosList_CODIGO from PRODUCTO_BODEGA where bodegasList_CODIGO =" + codigoBodega);
        List objectList = query.getResultList();
        if(!objectList.isEmpty()){
            objectList.forEach(e-> {
                codigoProductos.add(Integer.valueOf(valueOf(e)));
            });
        }
        return codigoProductos;
    }

    private List<Producto> productos;
    public List<Producto> getProductos(int codigoBodega, int codigoCategoria){
        productos = new ArrayList<>();
        List<Integer> codigosProducto = getProductosPorBodega(codigoBodega);
        if(!codigosProducto.isEmpty()) {
            codigosProducto.forEach(e -> productos.add(super.find(e)));
            productos = productos
                    .parallelStream()
                    .filter(producto -> producto.getCategoria().getCodigo() == codigoCategoria)
                    .collect(Collectors.toList());
            List<Producto> productoList = new ArrayList<>();

            productos.forEach(p -> {
                Producto producto = new Producto(p.getCodigo(), p.getNombre(), p.getImagen(), p.getPrecioCompra(),
                        p.getPrecioVenta(), p.getIva(), p.getStock(), null, null, null);
                productoList.add(producto);
            });


            return productoList;
        }
        return productos;
    }

    private List<Categoria> categoriasList;

    public List<Categoria> getCategorias(int codigoBodega){
        categoriasList = new ArrayList<>();
        List<Integer> codigosProductos = getProductosPorBodega(codigoBodega);

        if(!codigosProductos.isEmpty()){
            codigosProductos.forEach(e->{
                categoriasList.add(super.find(e).getCategoria());
            });
            Set<Categoria> categorias = new HashSet<>(categoriasList);
            categoriasList.clear();
            categorias.forEach(e->{e.setProductosList(null); categoriasList.add(e);});
            return categoriasList;
        }else
            return new ArrayList<>();
    }

}
