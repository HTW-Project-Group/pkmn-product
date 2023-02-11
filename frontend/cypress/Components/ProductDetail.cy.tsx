import * as React from 'react'
import ProductDetail from "../../src/Components/Product/ProductDetail"
import Card from "../../src/Model/Card"

describe('Header.cy.ts', () => {
    it('renders a pokemons details', () => {
        const productMock: Card = {
            condition: 10,
            pokemonId: "swsh9-166",
            id: "64f75d2e-3677-4ed8-84c9-70e51ded0aec",
            name: "Arceus V",
            price: 271.72,
            description: "Sunt sed voluptas sit et modi minus nihil qui id tempore quo voluptatum sequi enim est aut in dolorem dolores deserunt porro enim reiciendis quia soluta molestiae sed rerum ratione quia perspiciatis autem ut exercitationem vel assumenda ipsum labore praesentium architecto et atque est eum maxime illum illo doloribus rerum molestiae consequatur autem asperiores sequi eos nostrum et cupiditate nemo nemo eveniet ea culpa eos iusto placeat sapiente occaecati sequi repellendus sint velit minima veritatis est eligendi provident suscipit voluptatem in sint dolor accusamus deleniti at itaque suscipit sapiente omnis at qui ut temporibus fugiat voluptas id nulla nulla natus."
        }
        cy.mount(<ProductDetail description={productMock.description} condition={productMock.condition} id={productMock.id}
                                name={productMock.name} pokemonId={productMock.pokemonId} price={productMock.price}/>)
        cy.intercept('GET','/v1/pokemon/**', {
            statusCode: 201,
            body: {
                id: "swsh9-166",
                name: "Arceus V",
                supertype: "Pokémon",
                subtypes: [
                    "Basic",
                    "V"
                ],
                level: null,
                hp: "220",
                types: [
                    "Colorless"
                ],
                attacks: [
                    {
                        name: "Trinity Charge",
                        cost: [
                            "Colorless",
                            "Colorless"
                        ],
                        convertedEnergyCost: 2,
                        damage: "",
                        text: "Search your deck for up to 3 basic Energy cards and attach them to your Pokémon V in any way you like. Then, shuffle your deck."
                    },
                    {
                        name: "Power Edge",
                        cost: [
                            "Colorless",
                            "Colorless",
                            "Colorless"
                        ],
                        convertedEnergyCost: 3,
                        damage: "130",
                        text: ""
                    }
                ],
                weaknesses: [
                    {
                        type: "Fighting",
                        value: "×2"
                    }
                ],
                resistances: null,
                set: {
                    id: "swsh9",
                    name: "Brilliant Stars",
                    series: "Sword & Shield",
                    printedTotal: 172,
                    total: 186,
                    ptcgoCode: "BRS",
                    releaseDate: "2022/02/25"
                },
                number: "166",
                artist: "kawayoo",
                rarity: "Rare Ultra"
            }
        })
    })
})