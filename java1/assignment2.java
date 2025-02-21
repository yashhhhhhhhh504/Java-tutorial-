import java.util.*;
import java.util.Map.Entry;

interface Admin {
    int checker(String username, String password);

    void addcategory(int categoryid, String categoryname);

    void removecategory(int categoryid);

    void removeproduct(int categoryid, int productid);

    void showcategory();

}

class Adminfunctions implements Admin {
    private String adminusername = "admin";
    private String adminpassword = "password";
    public HashMap<Integer, String> category = new HashMap<>();
    public HashMap<Integer, List<Integer>> product = new HashMap<>();//
    public HashMap<Integer, List<String>> productdetails = new HashMap<>();// stores the product details
    public ArrayList<Integer> giveaway = new ArrayList<>();
    public ArrayList<Integer> giveawaydetails = new ArrayList<>();

    public int checker(String username, String password) {
        if (username.equals(adminusername) && password.equals(adminpassword)) {
            return 1;
        } else {
            return 0;
        }
    }

    public void addcategory(int categoryid, String categoryname) {
        category.put(categoryid, categoryname);
        System.out.println(
                "the category name " + categoryname + " with category id " + categoryid + " is added to the database");

    }

    public void removecategory(int categoryid) {
        category.remove(categoryid);
        System.out.println("the category with category id " + categoryid + " is removed from the database");

    }

    public void addproduct(int categoryid, int productid) {
        List<Integer> productidlist = new ArrayList<>();
        if (product.containsKey(categoryid)) {
            productidlist = product.get(categoryid);
            productidlist.add(productid);
            product.put(categoryid, productidlist);
        } else {
            productidlist.add(productid);
            product.put(categoryid, productidlist);
        }
        System.out.println("the product with product id " + productid + " is added to the database");
    }

    public void addproductdetails(int productid, String productname, String productdescription, int productprice,
            int productquantity) {
        List<String> productdetailslist = new ArrayList<>();
        productdetailslist.add(productname);
        productdetailslist.add(productdescription);
        productdetailslist.add(Integer.toString(productprice));
        productdetailslist.add(Integer.toString(productquantity));
        productdetails.put(productid, productdetailslist);
        System.out.println("the product details of product id " + productid + " and the product name " + productname
                + " is added to the database");
    }

    public void removeproduct(int categoryid, int productid) {
        List<Integer> productidlist = new ArrayList<>();
        productidlist = product.get(categoryid);
        productidlist.remove(Integer.valueOf(productid));
        product.put(categoryid, productidlist);
        System.out.println("the product with product id " + productid + " is removed from the database");
    }

    public void setdiscount(int productid, int discountprice, int primecustomerdiscountprice,
            int elitecustomerdiscountprice) {
        List<String> productdetailslist = new ArrayList<>();
        productdetailslist = productdetails.get(productid);
        productdetailslist.add(Integer.toString(discountprice));
        productdetailslist.add(Integer.toString(primecustomerdiscountprice));
        productdetailslist.add(Integer.toString(elitecustomerdiscountprice));
        productdetails.put(productid, productdetailslist);
        System.out.println("the discount price of product id " + productid + " is set to " + discountprice);
    }

    public void addgiveaway(int productid1, int productid2, int combinedpriceint) {
        giveaway.add(productid1);
        giveaway.add(productid2);
        giveaway.add(combinedpriceint);
        System.out.println("the giveaway of product id " + productid1 + " and product id " + productid2
                + " is added to the database");
    }

    public void showcategory() {
        System.out.println("Category id \t Category name");
        System.out.println("====================================");
        for (Entry<Integer, String> entry : category.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
        }
    }
}

interface Customer {
    int checker(String username, String password);

    void addtocart(int productid, int quantity);

    void adddetails(String name, String address, String phonenumber, String emailid, String username2);

    void upgrademembership(String username, String membershipp);
}

class Customerfunctions extends Adminfunctions implements Customer {
    private HashMap<String, String> customerinfo = new HashMap<>();
    private HashMap<String, List<String>> customerdetails = new HashMap<>();
    public HashMap<Integer, List<Integer>> cart = new HashMap<Integer, List<Integer>>();
    public HashMap<String, List<String>> membership = new HashMap<String, List<String>>();
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void logincustomer() {
        customerinfo.put(this.username, this.password);
    }

    public void adddetails(String name, String address, String phonenumber, String emailid, String username2) {
        List<String> customerdetailslist = new ArrayList<>();
        customerdetailslist.add(name);
        customerdetailslist.add(address);
        customerdetailslist.add(phonenumber);
        customerdetailslist.add(emailid);
        customerdetails.put(this.username, customerdetailslist);
        System.out.println("the customer details of customer " + this.username + " is added to the database");
    }

    public int checker(String username, String password) {
        if (customerinfo.containsKey(username)) {
            if (customerinfo.get(username).equals(password)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void addtocart(int productid, int quantity) {
        if (cart.containsKey(productid)) {
            if (quantity < Integer.parseInt(productdetails.get(productid).get(4))) {
                List<Integer> temp = cart.get(productid);
                temp.add(quantity);
                cart.put(productid, temp);
            } else {
                System.out.println("The quantity is not available");
            }
        } else {
            if ((quantity < Integer.parseInt(productdetails.get(productid).get(4)))) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(quantity);
                cart.put(productid, temp);
            } else {
                System.out.println("The quantity is more than the available quantity");
            }
        }
    }

    public void emptycart() {
        cart.clear();
        System.out.println("your cart has been cleared!");
    }

    public void membershipstatus(String username) {
        if (membership.containsKey(username)) {
            System.out.println("The membership status is " + membership.get(username).get(0));
        } else {
            List<String> temp = new ArrayList<String>();
            temp.add(username);
            membership.put("Normal", temp);
            System.out.println("the" + username + "has the status of a normal customer");
        }
    }

    public void upgrademembership(String username, String membershipp) {
        if (membership.equals("Prime")) {
            List<String> temp = new ArrayList<String>();
            temp.add(username);
            membership.put("Prime", temp);
            System.out.println("the" + username + "has the status of a Gold customer");
        } else if (membership.equals("Elite")) {
            List<String> temp = new ArrayList<String>();
            temp.add(username);
            membership.put("Elite", temp);
            System.out.println("the" + username + "has the status of a Silver customer");
        } else if (membership.equals("Normal")) {
            List<String> temp = new ArrayList<String>();
            temp.add(username);
            membership.put("Normal", temp);
            System.out.println("the" + username + "has the status of a Normal customer");
        } else {
            System.out.println("The membership is not available");
        }
    }

    public void viewcart() {
        for (Map.Entry<Integer, List<Integer>> entry : cart.entrySet()) {
            List<Integer> temp = entry.getValue();
            for (Map.Entry<Integer, List<String>> temp1 : productdetails.entrySet()) {
                System.out.println("The product name is " + temp1.getValue().get(0) + "And the product id is "
                        + temp1.getKey() + "And the product 2 " + temp1.getValue().get(1) + "and the price is "
                        + temp1.getValue().get(2) + "and the quantity is " + temp.get(0));
            }
        }
    }
}

interface Catelog {
    void showproduct(int product);

    void showdeals();
}

class Catelogfunctions extends Customerfunctions implements Catelog {
    HashMap<Integer, List<Integer>> coupons = new HashMap<Integer, List<Integer>>();

    public void showdeals() {
        System.out.println("the giveaway deals  are:");
        for (int i = 0; i < giveaway.size(); i = i + 3) {
            System.out.println("the product id " + giveaway.get(i) + " and the product id " + giveaway.get(i + 1)
                    + " is given away at the price of " + giveaway.get(i + 2));
        }
    }

    public void showproduct(int product) {
        System.out.println("The product details are:");
        for (Map.Entry<Integer, List<String>> entry : productdetails.entrySet()) {
            if (entry.getKey() == product) {
                System.out.println("product id: " + entry.getKey() + " product details: " + entry.getValue());
            }
        }
    }

    public void checkdeals(String username) {
        if (membership.get(username).get(0).equals("Prime")) {
            System.out.println("The deals are:");
            for (int i = 0; i < membership.size(); i = i + 3) {
                System.out.println("the product id " + membership.get(i) + " and the product id "
                        + membership.get(i + 1) + " is given away at the price of " + membership.get(i + 2));
            }
        } else if (membership.get(username).get(0).equals("Elite")) {
            System.out.println("The deals are:");
            for (int i = 0; i < membership.size(); i = i + 3) {
                System.out.println("the product id " + membership.get(i) + " and the product id "
                        + membership.get(i + 1) + " is given away at the price of " + membership.get(i + 2));
            }
        } else if (membership.get(username).get(0).equals("Normal")) {
            System.out.println("The deals are:");
            for (int i = 0; i < membership.size(); i = i + 3) {
                System.out.println("the product id " + membership.get(i) + " and the product id "
                        + membership.get(i + 1) + " is given away at the price of " + membership.get(i + 2));
            }
        } else {
            System.out.println("The deals are:");
            for (int i = 0; i < membership.size(); i = i + 3) {
                System.out.println("the product id " + membership.get(i) + " and the product id "
                        + membership.get(i + 1) + " is given away at the price of " + membership.get(i + 2));
            }
        }
    }

    public int getrandom(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void setcoupons() {
        for (Map.Entry<Integer, List<String>> entry : productdetails.entrySet()) {
            List<String> temp = entry.getValue();
            int random = getrandom(5, 15);
            if (random < 50) {
                List<Integer> temp1 = new ArrayList<Integer>();
                temp1.add(random);
                temp1.add(Integer.parseInt(temp.get(2)));
                temp1.add(Integer.parseInt(temp.get(2)) - random);
                coupons.put(entry.getKey(), temp1);
            }
        }
    }

}

interface billing {
    void displaybalance(String username);

    void walletadd(int money, String username);

}

class Billingfunctions extends Catelogfunctions implements billing {
    public HashMap<String, Integer> wallet = new HashMap<String, Integer>();
    public HashMap<Integer, List<Integer>> finalprices = new HashMap<Integer, List<Integer>>();

    public void displaybalance(String username) {
        if (wallet.containsKey(username)) {
            System.out.println("The balance is " + wallet.get(username));
        } else {
            System.out.println("The user has no balance or the person has not been regestered");
        }
    }

    public void walletadd(int money, String username) {
        if (wallet.containsKey(username)) {
            int temp = wallet.get(username);
            temp = temp + money;
            wallet.put(username, temp);
        } else {
            wallet.put(username, money);
        }
    }

    public void finalbilling(String username) {
        int total = 0;
        for (Map.Entry<Integer, List<Integer>> entry : cart.entrySet()) {
            List<Integer> temp = entry.getValue();
            for (Map.Entry<Integer, List<String>> temp1 : productdetails.entrySet()) {
                total = total + (Integer.parseInt(temp1.getValue().get(2)) * temp.get(0));
            }
        }
        if (membership.containsKey(username)) {
            if (membership.get(username).get(0).equals("Prime")) {
                total = total - (total * 5 / 100);
            } else if (membership.get(username).get(0).equals("Elite")) {
                total = total - (total * 10 / 100);
            } else if (membership.get(username).get(0).equals("Normal")) {
                total = total - (total * 0);
            }
        }
        System.out.println("The total bill is " + total);
        if (wallet.containsKey(username)) {
            int temp = wallet.get(username);
            if (temp > total) {
                temp = temp - total;
                wallet.put(username, temp);
                System.out.println("The balance is " + temp);
            } else {
                System.out.println("The balance is not sufficient");
            }
        } else {
            System.out.println("The balance is not sufficient");
        }

    }
}

public class assignment2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Adminfunctions admin = new Adminfunctions();
        Customerfunctions customer = new Customerfunctions();
        Catelogfunctions catelog = new Catelogfunctions();
        Billingfunctions billing = new Billingfunctions();
        while (true) {
            System.out.println("Welcome to “FLIPZON");
            System.out.println("1. Enter as admin!");
            System.out.println("2. Explore the product catalog");
            System.out.println("3. Enter as customer");
            System.out.println("4. Show available deals");
            System.out.println("5. Exit the Apllication");
            int ch = sc.nextInt();
            if (ch == 1) {
                System.out.println("Enter the username ");
                String username = sc.next();
                System.out.println("Enter the password ");
                String password = sc.next();
                if (admin.checker(username, password) == 1) {
                    System.out.println("Welcome admin");
                    System.out.println("1. Add category ");
                    System.out.println("2. Delete category");
                    System.out.println("3. Add product ");
                    System.out.println("4. Delete product");
                    System.out.println("5. Set discount price");
                    System.out.println("6. Add giveaway deal");
                    System.out.println("7. back");
                    int ch2 = sc.nextInt();
                    if (ch2 == 1) {
                        System.out.println("Enter the category name");
                        sc.nextLine();
                        String categoryname = sc.nextLine();
                        System.out.println("Enter the category id");
                        int categoryid = sc.nextInt();
                        admin.addcategory(categoryid, categoryname);

                    } else if (ch2 == 2) {
                        System.out.println("Enter the category which you want to remove:");
                        int categoryid = sc.nextInt();
                        admin.removecategory(categoryid);

                    } else if (ch2 == 3) {
                        System.out.println("Enter the Category id in which you want to add the product");
                        int categoryid = sc.nextInt();
                        System.out.println("Enter the product id");
                        int productid = sc.nextInt();
                        admin.addproduct(categoryid, productid);
                        System.out.println("Enter the product name");
                        sc.nextLine();
                        String productname = sc.nextLine();
                        System.out.println("Enter the product price");
                        int productprice = sc.nextInt();
                        System.out.println("Enter the product quantity");
                        int productquantity = sc.nextInt();
                        System.out.println("Enter the product description");
                        sc.nextLine();
                        String productdescription = sc.nextLine();
                        admin.addproductdetails(productid, productname, productdescription, productprice,
                                productquantity);

                    } else if (ch2 == 4) {
                        System.out.println("Enter the category id in which you want to remove the product");
                        int categoryid = sc.nextInt();
                        System.out.println("Enter the product id");
                        int productid = sc.nextInt();
                        admin.removeproduct(categoryid, productid);

                    } else if (ch2 == 5) {
                        System.out.println("Enter the product id in which you want to set disount:");
                        int productid = sc.nextInt();
                        System.out.println("Enter the discount price for Normal customer");
                        int discountprice = sc.nextInt();
                        System.out.println("Enter the discount price for Prime customer");
                        int primecustomerdiscountprice = sc.nextInt();
                        System.out.println("Enter the discount price for Elite customer");
                        int elitecustomerdiscountprice = sc.nextInt();
                        admin.setdiscount(productid, discountprice, primecustomerdiscountprice,
                                elitecustomerdiscountprice);
                    } else if (ch2 == 6) {
                        System.out.println("Enter the first product id which you want to add to giveaway");
                        int productid1 = sc.nextInt();
                        System.out.println("Enter the second product id which you want to add to giveaway");
                        int productid2 = sc.nextInt();
                        System.out.println("Enter the combined price whcih you want to give");
                        int combinedprice = sc.nextInt();
                        admin.addgiveaway(productid1, productid2, combinedprice);
                    } else if (ch2 == 7) {
                        break;
                    }

                } else {
                    System.out.println("Invalid username or password");
                }
            } else if (ch == 2) {
                admin.showcategory();

            } else if (ch == 3) {
                System.out.println("1. Signup");
                System.out.println("2. login ");
                System.out.println("3. back");
                int ch3 = sc.nextInt();
                if (ch3 == 1) {
                    System.out.println("Enter the details to register yourself!\n");
                    System.out.println("Welcome to the signup page");
                    System.out.println("Enter your name");
                    String name = sc.next();
                    System.out.println("Enter your email id ");
                    String email = sc.next();
                    System.out.println("Enter your phone number");
                    String phone = sc.next();
                    System.out.println("Enter the username which you want to set");
                    String username = sc.next();
                    System.out.println("Enter the password which you want to keep:");
                    String password = sc.next();
                    System.out.println("Re-enter the password");
                    String password1 = sc.next();
                    if (password1.equals(password)) {
                        customer.setUsername(username);
                        customer.setPassword(password);
                        customer.logincustomer();
                        customer.adddetails(name, email, phone, password, username);
                    } else {
                        System.out.println("Password does not match");
                    }
                } else if (ch3 == 2) {
                    System.out.println("Enter your username");
                    String username = sc.next();
                    System.out.println("Enter your password");
                    String password = sc.next();
                    if (customer.checker(username, password) == 1) {
                        System.out.println("Welcome " + username);
                        System.out.println("Welcome" + username);
                        System.out.println("1.  Browse products ");
                        System.out.println("2.  Browse deals");
                        System.out.println("3.  add product to the cart");
                        System.out.println("4.  add products in the deal of the cart");
                        System.out.println("5.  view coupons");
                        System.out.println("6.  check account balance ");
                        System.out.println("7.  clear cart");
                        System.out.println("8.  view cart");
                        System.out.println("9.  checkout cart ");
                        System.out.println("10. upgrade customer status");
                        System.out.println("11. add amount to wallet");
                        System.out.println("12. back");
                        int choicee = sc.nextInt();
                        if (choicee == 1) {
                            System.out.println("Enter the product id which you want to add to cart");
                            int productid = sc.nextInt();
                            catelog.showproduct(productid);

                        } else if (choicee == 2) {
                            catelog.checkdeals(username);

                        } else if (choicee == 3) {
                            System.out.println("Enter the product id which you wan to add in your cart:");
                            int productid = sc.nextInt();
                            System.out.println("Enter the quantity of the product which you want to add:");
                            int quantity = sc.nextInt();
                            customer.addtocart(productid, quantity);

                        } else if (choicee == 4) {
                            System.out.println("Enter your username which you want to add in your cart:");
                            String username1 = sc.next();
                            catelog.checkdeals(username1);

                        } else if (choicee == 5) {

                        } else if (choicee == 6) {
                            System.out.println("Enter the username");
                            String username1 = sc.next();
                            billing.displaybalance(username1);
                        } else if (choicee == 7) {
                            customer.emptycart();
                        } else if (choicee == 8) {
                            customer.viewcart();
                        } else if (choicee == 9) {
                            System.out.println("Enter the username to continue:");
                            String username1 = sc.next();
                            System.out.println("Enter the password to continue:");
                            String password1 = sc.next();
                            if (customer.checker(username1, password1) == 1) {
                                billing.finalbilling(username1);
                            } else {
                                System.out.println("Invalid username or password");
                            }
                        } else if (choicee == 10) {
                            System.out.println("Enter your username:");
                            String username2 = sc.next();
                            System.out.println("Do you wish to see your current status?");
                            String status = sc.next();
                            if (status.equals("yes")) {
                                customer.membershipstatus(username2);
                            } else {
                                System.out.println("Enter your password:");
                                String password2 = sc.next();
                                if (customer.checker(username2, password2) == 1) {
                                    System.out.println("enter the membership which you want to upgrade to:");
                                    String membership = sc.next();
                                    customer.upgrademembership(username2, membership);
                                } else {
                                    System.out.println("Wrong username or password");
                                }
                            }
                        } else if (choicee == 11) {
                            System.out.println("Enter the amount which you want to add:");
                            int amount = sc.nextInt();
                            System.out.println("Enter your username to comfirm the addition:");
                            String username2 = sc.next();
                            System.out.println("Enter your password to comfirm the addition:");
                            String password2 = sc.next();
                            if (customer.checker(username2, password2) == 1) {
                                billing.walletadd(amount, username2);
                            } else {
                                System.out.println("Wrong username or password");
                            }
                        } else if (choicee == 12) {
                            break;
                        }
                    }
                } else if (ch3 == 3) {
                    break;
                }
            } else if (ch == 4) {
                catelog.showdeals();
            } else if (ch == 5) {
                System.out.println("Thank you for using the application!");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
