export interface Student {
    id: number;
    fullName: string;
    parcours?: {
      id: number;
      parcourName: string;
    };
  }
  