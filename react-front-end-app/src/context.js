import React, { Component } from 'react';

const ProductContext=React.createContext();


class ProductProvider extends Component {

    constructor(props)
    {
        super(props);
        this.state={
            products:[],
            productSubTotal: 0,
            productTotal:0,
            isLoaded:false,
            totalPrice:{},
            modalOpen:false

        }
    }

    componentDidMount()
    {
         this.getAllProducts();
    }

    getAllProducts=()=>{
        fetch('http://localhost:9090/products')
        .then(res=>res.json())
        .then(result=>{
           this.setState({
               isLoaded:true,
               products:result
           },()=>{
               console.log(result);
           });
        },error=>{
            this.setState({
                isLoaded:true,
                error
            })
        }
        );
    }

    incrementAndDecrement=()=>{

        fetch('http://localhost:9090/price/total',{
            method:'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type':'application/json'
            },
            body: JSON.stringify({

                    products:this.state.products
                
            })
        }).then(res=>{
            console.log(res);
            return res.json();
        })
        .then(result=>{
           this.setState({
               isLoaded:true,
               totalPrice:result,
               products:result.products
           },()=>{
            this.state.totalPrice.products.forEach(p=>{
                console.log(`price By Single Unit ${p.priceByUnit}`);
            })
           })
        },error=>{
            this.setState({
                isLoaded:true,
                error
            })
        });

    }

    applyChanges=()=>{

        fetch('http://localhost:9090/price/total',{
            method:'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type':'application/json'
            },
            body: JSON.stringify({

                    products:this.state.products
                
            })
        }).then(res=>{
            console.log(res);
            return res.json();
        })
        .then(result=>{
           this.setState({
               isLoaded:true,
               products:result.products
           })
        },error=>{
            this.setState({
                isLoaded:true,
                error
            })
        });

    }
    getProduct=id=>{
         const product=this.state.products.find(prod=>prod.productId===id);
         return product;
    }

    openModal=()=>{
         this.setState(()=>{
             return {modalOpen:true}
         })
    }

    closeModal=()=>{
         this.setState(()=>{
            return { modalOpen:false}
         })
    }

    increment=(id)=>{

        let tempProducts=[...this.state.products];

        const selectedProduct=tempProducts.find(prod=>prod.productId===id);

        const index=tempProducts.indexOf(selectedProduct);

        const product=tempProducts[index];

        product.quantity=product.quantity+1;

         this.incrementAndDecrement();
    }

    description=(id)=>{
         console.log(`description is clicked by ${id}`)
    }

    decrement=(id)=>{
        
        let tempProducts=[...this.state.products];

        const selectedProduct=tempProducts.find(prod=>prod.productId===id);

        const index=tempProducts.indexOf(selectedProduct);

        const product=tempProducts[index];

        product.quantity=product.quantity-1;

        this.incrementAndDecrement();
    }

    render() {
        return (
            <ProductContext.Provider value={{...this.state,
            increment:this.increment,
            decrement:this.decrement,
            description:this.description,
            openModal:this.openModal,
            closeModal:this.closeModal}}>
                {this.props.children}
                </ProductContext.Provider>
        );
    }
}

const ProductConsumer=ProductContext.Consumer;

export {ProductContext,ProductProvider,ProductConsumer};