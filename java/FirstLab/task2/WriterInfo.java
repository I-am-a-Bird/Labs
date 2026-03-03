public class WriterInfo {
    public void printInfo(Product product){
        System.out.println("====Product info====");
        System.out.println("Product Name:"+product.getName());
        System.out.println("price:"+product.getPrice()+"$");
        String Status = product.getAvailability()?"Yes":"No";
        System.out.println("Availavility:"+Status);
    }
}
