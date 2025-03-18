package com.taller01.clase_review.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "reviews")
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El título no puede estar vacío")
    private String title;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer rating;

    @NotEmpty(message = "El comentario no puede estar vacío")
    @Column(columnDefinition = "TEXT")
    private String comment;

    @NotEmpty(message = "El modelo del auto no puede estar vacío")
    private String carModel;

    // Constructor por defecto
    public Review() {
    }

    // Constructor con parámetros
    public Review(String title, Integer rating, String comment, String carModel) {
        this.title = title;
        this.rating = rating;
        this.comment = comment;
        this.carModel = carModel;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    // toString method para debugging
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", carModel='" + carModel + '\'' +
                '}';
    }
}