export class Transaction {
    constructor();
    constructor(public amount?: number,
                public type?: string,
                public depositorAccount?: string,
                public receiverAccount?: string,
                public transferDate?: Date,
                public createdAt?: Date        
            ) {
    }
}
