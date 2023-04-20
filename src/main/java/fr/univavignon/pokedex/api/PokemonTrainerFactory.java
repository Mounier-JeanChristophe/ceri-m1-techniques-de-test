package fr.univavignon.pokedex.api;

public class PokemonTrainerFactory implements IPokemonTrainerFactory{

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        PokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
        PokemonFactory pokemonFactory = new PokemonFactory();
        IPokedex pokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);

        return new PokemonTrainer(name, team, pokedex);
    }
}
