package Ex10;

public enum phoneModel {
    IPHONE(9999), HUAWEI(8888), PIXEL(6666), SAMSUNG(9399), LG(5588);
    private final int price;

    public int getPrice() {
        return price;
    }

    phoneModel(int price) {
        this.price = price;
    }
}
