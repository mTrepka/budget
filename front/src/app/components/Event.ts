export interface Event {
  moneyId: number;
  evName: string;
  type: string;
  value: number;
  eventDate: string;
  creationDate: any;
  category: {
    categoryId: number;
    name: string;
  };
}
