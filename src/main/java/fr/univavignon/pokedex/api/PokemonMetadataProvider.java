package fr.univavignon.pokedex.api;

import java.util.ArrayList;

public class PokemonMetadataProvider implements IPokemonMetadataProvider{

    private final ArrayList<PokemonMetadata> pokemonMedatataList;

    public PokemonMetadataProvider() {
        this.pokemonMedatataList = new ArrayList<>();
        pokemonMedatataList.add(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        pokemonMedatataList.add(new PokemonMetadata(133, "Aquali", 186, 168, 260));
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        for(PokemonMetadata pokemonMetadata : pokemonMedatataList){
            if(pokemonMetadata.getIndex() == index){
                return pokemonMetadata;
            }
        }
        throw new PokedexException("PokemonMetadata introuvable pour l'index:" + index);
    }
}
