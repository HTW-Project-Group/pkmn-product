import * as React from 'react'
import RecommendedProducts from "../../src/Components/RecommendedProducts";
import {BrowserRouter} from "react-router-dom";

describe('RecommendedProducts.cy.ts', () => {
    it('routes to detail page when clicking on product', () => {
        cy.mount(
            <BrowserRouter>
                <RecommendedProducts/>
            </BrowserRouter>
        )
        cy.get('[data-cy="card"]').first().click();
        cy.location('pathname').should('eq', '/details')
    })
})