package xyz.pepefab.ultrapixelmon.Getter;

public class Pokemons {

    private String nomDuPokemon;
    private long heureSpawnPokemon;
    private String nomDuJoueurCapturantLePokemon;
    private String talent;
    private double ivsPourcentage;

    public Pokemons(String nomDuPokemon, long heureSpawnPokemon, String nomDuJoueurCapturantLePokemon, String talent, double ivsPourcentage) {
        this.nomDuPokemon = nomDuPokemon;
        this.heureSpawnPokemon = heureSpawnPokemon;
        this.nomDuJoueurCapturantLePokemon = nomDuJoueurCapturantLePokemon;
        this.talent = talent;
        this.ivsPourcentage = ivsPourcentage;
    }

    public String getNomDuPokemon() {
        return nomDuPokemon;
    }

    public long getHeureSpawnPokemon() {
        return heureSpawnPokemon;
    }

    public String getNomDuJoueurCapturantLePokemon() {
        return nomDuJoueurCapturantLePokemon;
    }

    public String getTalent(){
        return talent;
    }

    public double getIvsPourcentage(){
        return ivsPourcentage;
    }


    public static Pokemons saveInfoPokemon(String nomDuPokemon, long heureSpawnPokemon, String nomDuJoueurCapturantLePokemon, String talent, double ivsPourcentage){
        return new Pokemons(nomDuPokemon, heureSpawnPokemon, nomDuJoueurCapturantLePokemon, talent, ivsPourcentage);
    }
}
