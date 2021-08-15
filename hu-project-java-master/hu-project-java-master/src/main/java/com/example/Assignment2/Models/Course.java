package com.example.Assignment2.Models;

import javax.persistence.*;

@Entity
@Table()
public class Course {

    @Id
    @SequenceGenerator(
            name="course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )

    public long id;
    public String name;
    public String domain;
    public String description;
    public String author;
    public int price;
    public String duration;
    public String content;
    public int cartCount;
    public int wishlistCount;
    public int purchaseCount;
    float rating;

    public Course() {
    }

    public Course(long id, String name, String domain, String description, String author, int price, String duration, String content,int cartCount,
             int wishlistCount,int purchaseCount) {
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.description = description;
        this.author = author;
        this.price = price;
        this.duration = duration;
        this.content = content;
        this.cartCount=cartCount;
        this.wishlistCount=wishlistCount;
        this.purchaseCount=purchaseCount;
    }

    public Course(String name, String domain, String description, String author, int price, String duration, String content ,int cartCount,
                  int wishlistCount,int purchaseCount) {
        this.name = name;
        this.domain = domain;
        this.description = description;
        this.author = author;
        this.price = price;
        this.duration = duration;
        this.content = content;
        this.cartCount=cartCount;
        this.wishlistCount=wishlistCount;
        this.purchaseCount=purchaseCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public int getWishlistCount() {
        return wishlistCount;
    }

    public void setWishlistCount(int wishlistCount) {
        this.wishlistCount = wishlistCount;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", duration='" + duration + '\'' +
                ", content='" + content + '\'' +
                ", cartCount=" + cartCount +
                ", wishlistCount=" + wishlistCount +
                ", purchaseCount=" + purchaseCount +
                '}';
    }
}
