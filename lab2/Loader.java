class Loader { 
    private final int id;
    private final Cruise cruiseServing; 

    public Loader(int id) {
        this.id = id;
        cruiseServing = null;  
    }
    private Loader(int id, Cruise cruise)  {
        this.id = id; 
        cruiseServing = cruise;
    }
    public boolean canServe(Cruise cruise)  {
        if(cruiseServing == null) { 
            return true;
        } else if (cruiseServing.getServiceCompletionTime() <=  cruise.getArrivalTime())  { 
            return true; 
        } else { 
            return false; 
        }
    }

    public Loader serve(Cruise cruise)    {
        if(canServe(cruise)) {
            return new Loader(id, cruise); 
        } else  {
            return null; 
        }
    }

    @Override 
        public String toString()    {
            if (cruiseServing == null)    {
                return "Loader " + id; 
            } else {
                return "Loader " + id + " serving "  + cruiseServing;
            }
        }
}
