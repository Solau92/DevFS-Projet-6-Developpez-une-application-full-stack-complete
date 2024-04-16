import { User } from "src/app/model/user.interface";

export interface Comment {
    id: number,
    auteur: User,
    content: string
}