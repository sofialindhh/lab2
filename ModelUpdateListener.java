public interface ModelUpdateListener {
    /*
    metod som notifierar lyssnare om uppdatering
    anitngen att uppdatera generellt, t.ex. repaint
    eller lägga till ny bild, här när vi lägger till bil
    eller ta bort bild, här ta bort bild från view

     */
    void updateModel();
    void updateNewImage(String id, String carType, int posX, int posY);
    void updateRemoveImage(String id);
    void updateNewPosition(String id, int posX, int posY);
}
