package controlador;

public interface OnHttpRequestComplete {
	
	/**
     * Este m�todo es ejecutado al terminar la ejecucion del m�todo excecute del httpclient
     * devuelve un Response, con los datos de la solicitud
     * @param status Instancia de la clase Response
     */
    public void onComplete(Response status);

}
