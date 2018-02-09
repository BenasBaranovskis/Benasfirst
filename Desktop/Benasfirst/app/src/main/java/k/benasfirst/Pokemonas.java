package k.benasfirst;


public class Pokemonas {
    private int id;
   private String name;
    private double height;
   private double weight;
   private String hostility;
   private String ToDo;
   private String car;

            public Pokemonas(int id, String name, double weight, double height, String hostility, String ToDo, String car) {
                this.id = id;
                this.name = name;
                this.weight = weight;
                this.height = height;
                this.hostility = hostility;
                this.ToDo = ToDo;
                this.car = car;
            }

            public Pokemonas() {
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
               this.id = id;
            }

            public String getName() {
               return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

           public double getHeight() {
                return height;
            }

            public void setHeight(double height) {
                this.height = height;
            }

            public String getHostility() {
                return hostility;
            }

            public void setHostility(String hostility) {
                this.hostility = hostility;
            }

            public String getToDo() {
                return ToDo;
            }

            public void setToDo(String toDo) {
                this.ToDo = toDo;
            }

            public String getCar() {
                return car;
            }

            public void setCar(String car) {
                this.car = car;
            }
}

