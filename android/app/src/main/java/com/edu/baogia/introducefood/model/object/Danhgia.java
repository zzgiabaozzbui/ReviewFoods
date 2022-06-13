package com.edu.baogia.introducefood.model.object;

public class Danhgia {
    private int idMonAn;
    private String tenTaiKhoan;
    private Float rating;

    public Danhgia() {
    }

    public Danhgia(int idMonAn, String tenTaiKhoan, Float rating) {
        this.idMonAn = idMonAn;
        this.tenTaiKhoan = tenTaiKhoan;
        this.rating = rating;
    }

    public int getIdMonAn() {
        return idMonAn;
    }

    public void setIdMonAn(int idMonAn) {
        this.idMonAn = idMonAn;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Danhgia{" +
                "idMonAn=" + idMonAn +
                ", tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", rating=" + rating +
                '}';
    }
}
