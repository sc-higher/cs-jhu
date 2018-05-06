/**
 *
 *
 * @author: Sean Connor
 */


public class Song
{
    // INITIALIZE VARIABLES

    String title;
    String itemCode;
    String description;
    String artist;
    String album;
    String price;

    // CONSTRUCTOR

    public Song(String title, String itemCode, String description,
                String artist, String album, String price)
    {
        this.title = title;
        this.itemCode = itemCode;
        this.description = description;
        this.artist = artist;
        this.album = album;
        this.price = price;
    }

    // GET + SET METHODS

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getItemCode()
    {
        return itemCode;
    }

    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public String getAlbum()
    {
        return album;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

}