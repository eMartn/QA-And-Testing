package assignment5;

public class Game
{
    private String name;
    private String genre;
    private double rating;
    private double price;

    // jackson databind, default constructor

    public Game()
    {

    }

    public Game(String name, String genre, double rating, double price)
    {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "Game{" + "name='" + name + '\'' + ", genre='" + genre + '\'' + ", rating=" + rating + ", price=" + price + '}';
    }
}
