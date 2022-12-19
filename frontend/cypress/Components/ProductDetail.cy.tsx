import * as React from 'react'
import ProductDetail from "../../src/Components/ProductDetail"

describe('Header.cy.ts', () => {
    it('renders random pkmn details', () => {
        cy.request("http://localhost:8080/v1/products?amount=1").then((data) => {
            const product = data.body[0];
            cy.mount(<ProductDetail description={product.description} condition={product.condition} id={product.id}
                                    name={product.name} pokemonId={product.pokemonId} price={product.price}/>)
        })
    })
})