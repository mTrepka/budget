export interface Event {
  moneyId: number;
  mName: string;
  type: string;
  value: number;
  eventDate: any;
  creationDate: any;
  category: {
    categoryId: number;
    name: string;
};
}
