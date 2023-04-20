package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory{
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) throws PokedexException {

        PokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
        PokemonMetadata pkmMetadata = pokemonMetadataProvider.getPokemonMetadata(index);

        String name = pkmMetadata.getName();
        int attack = pkmMetadata.getAttack();
        int defense = pkmMetadata.getDefense();
        int stamina = pkmMetadata.getStamina();
        double iv = Math.random() * 101;

        return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
    }
}
