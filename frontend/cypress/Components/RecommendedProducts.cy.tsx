import * as React from 'react'
import RecommendedProducts from "../../src/Components/Product/RecommendedProducts";
import {BrowserRouter} from "react-router-dom";
import Card from "../../src/Model/Card";

describe('RecommendedProducts.cy.ts', () => {
    it('routes to detail page when clicking on product', () => {
        cy.mount(
            <BrowserRouter>
                <RecommendedProducts/>
            </BrowserRouter>
        )

        const productMock: Card = {
            condition: 10,
            pokemonId: "swsh9-166",
            id: "64f75d2e-3677-4ed8-84c9-70e51ded0aec",
            name: "Arceus V",
            price: 271.72,
            description: "Sunt sed voluptas sit et modi minus nihil qui id tempore quo voluptatum sequi enim est aut in dolorem dolores deserunt porro enim reiciendis quia soluta molestiae sed rerum ratione quia perspiciatis autem ut exercitationem vel assumenda ipsum labore praesentium architecto et atque est eum maxime illum illo doloribus rerum molestiae consequatur autem asperiores sequi eos nostrum et cupiditate nemo nemo eveniet ea culpa eos iusto placeat sapiente occaecati sequi repellendus sint velit minima veritatis est eligendi provident suscipit voluptatem in sint dolor accusamus deleniti at itaque suscipit sapiente omnis at qui ut temporibus fugiat voluptas id nulla nulla natus."
        }

        const productMockArray: Card[] = new Array(7).fill(productMock);

        cy.intercept('GET', '/v1/products?amount=8', {
            body: productMockArray
        })
        cy.get('[data-cy="card"]').first().click();
        cy.location('pathname').should('eq', '/details/swsh9-166')
    })
})