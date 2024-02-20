import { LoanPage } from "./LoanPage";

export const LOANS_DATA: LoanPage = {
    content : [
        { id: 1, game : { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' }}, client : {id: 1, name : "ALejandro" }, initDate : new Date(2024,2,13) , finDate : new Date(2024,2,16)},
        { id: 2, game : { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' }}, client : {id: 1, name : "ALejandro" }, initDate : new Date(2024,2,13) , finDate : new Date(2024,2,16)},
        { id: 3, game : { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' }}, client : {id: 1, name : "ALejandro" }, initDate : new Date(2024,2,13) , finDate : new Date(2024,2,16)},
        { id: 4, game : { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' }}, client : {id: 1, name : "ALejandro" }, initDate : new Date(2024,2,13) , finDate : new Date(2024,2,16)},
        { id: 5, game : { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' }}, client : {id: 1, name : "ALejandro" }, initDate : new Date(2024,2,13) , finDate : new Date(2024,2,16)},
        { id: 6, game : { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' }}, client : {id: 1, name : "ALejandro" }, initDate : new Date(2024,2,13) , finDate : new Date(2024,2,16)},
        { id: 7, game : { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' }}, client : {id: 1, name : "ALejandro" }, initDate : new Date(2024,2,13) , finDate : new Date(2024,2,16)},
        { id: 8, game : { id: 1, title: 'Juego 1', age: 6, category: { id: 1, name: 'Categoría 1' }, author: { id: 1, name: 'Autor 1', nationality: 'Nacionalidad 1' }}, client : {id: 1, name : "ALejandro" }, initDate : new Date(2024,2,13) , finDate : new Date(2024,2,16)},

    ],
    pageable : {
        pageSize: 5,
        pageNumber: 0,
        sort: [
            {property: "id", direction: "ASC"}
        ]
    },
    totalElements: 8

}