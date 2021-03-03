package pis.hue1;

/**
 * Codec interface ist die  schnittstelle ,die vorgeschlagen wurde und muss die klasse wuerfel und caesar implementieren
 * @author Ngnindjeu sonfack doriane lovline
 */
public interface Codec {
    /**
     *
     * @param klartext Muessen wir es verschluesseln
     * @return es wird ein geheimttext zuruck gegeben werden
     */
    public abstract String kodiere(String klartext);

    /**
     *
     * @param geheimtext es wird entschluesselt
     * @return ein klartext wird zuruckgegeben werden
     *
     */
    public String dekodiere(String geheimtext);

    /**
     *
     * @return gibt die losung zuruck
     */
    public String gibLosung();

    /**
     *
     * @param schluessel  die losung wird gesetzt
     * @throws IllegalArgumentException bei ungeeignette schluessel die meldung wird geworfen
     */
    public void setzeLosung(String schluessel)throws IllegalArgumentException; // bei ungeeignetem Schluessel!

}
