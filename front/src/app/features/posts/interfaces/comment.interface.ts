import { User } from "src/app/interfaces/user.interface";

export interface Comment {
    id: number,
    auteur: User,
    content: string
}