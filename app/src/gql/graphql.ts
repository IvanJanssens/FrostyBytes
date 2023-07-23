/* eslint-disable */
import { TypedDocumentNode as DocumentNode } from '@graphql-typed-document-node/core';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
};

export type Category = {
  __typename?: 'Category';
  deleted: Scalars['Boolean']['output'];
  expiryPeriod: Scalars['String']['output'];
  icon: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  item: Array<Maybe<Item>>;
  name: Scalars['String']['output'];
};

export type Fridge = {
  __typename?: 'Fridge';
  deleted: Scalars['Boolean']['output'];
  id: Scalars['ID']['output'];
  items: Array<Maybe<Item>>;
  name: Scalars['String']['output'];
};

export type Item = {
  __typename?: 'Item';
  categoryEntity: Category;
  expirationDate: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
  placedAt: Scalars['String']['output'];
};

export type ItemInput = {
  categoryId: Scalars['ID']['input'];
  expirationDate: Scalars['String']['input'];
  name: Scalars['String']['input'];
  placedAt: Scalars['String']['input'];
};

export type Mutation = {
  __typename?: 'Mutation';
  addItemToFridge: Scalars['ID']['output'];
  createCategory: Scalars['ID']['output'];
  createFridge: Scalars['ID']['output'];
  deleteCategory: Scalars['ID']['output'];
  deleteFridge: Scalars['ID']['output'];
  deleteItem: Scalars['ID']['output'];
  updateCategory: Scalars['ID']['output'];
  updateFridge: Scalars['ID']['output'];
  updateItem: Scalars['ID']['output'];
};


export type MutationAddItemToFridgeArgs = {
  fridgeId: Scalars['ID']['input'];
  item: ItemInput;
};


export type MutationCreateCategoryArgs = {
  expiryPeriod: Scalars['String']['input'];
  icon: Scalars['String']['input'];
  name: Scalars['String']['input'];
};


export type MutationCreateFridgeArgs = {
  name: Scalars['String']['input'];
};


export type MutationDeleteCategoryArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteFridgeArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteItemArgs = {
  fridgeId: Scalars['ID']['input'];
  itemId: Scalars['ID']['input'];
};


export type MutationUpdateCategoryArgs = {
  icon: Scalars['String']['input'];
  id: Scalars['ID']['input'];
  name?: InputMaybe<Scalars['String']['input']>;
};


export type MutationUpdateFridgeArgs = {
  id: Scalars['ID']['input'];
  name: Scalars['String']['input'];
};


export type MutationUpdateItemArgs = {
  fridgeId: Scalars['ID']['input'];
  item: ItemInput;
  itemId: Scalars['ID']['input'];
};

export type Query = {
  __typename?: 'Query';
  /** @deprecated https://stackoverflow.com/questions/59868942/graphql-a-schema-must-have-a-query-operation-defined */
  ping?: Maybe<Scalars['String']['output']>;
};

export type Subscription = {
  __typename?: 'Subscription';
  getCategories: Array<Maybe<Category>>;
  getCategory: Category;
  getFridge?: Maybe<Fridge>;
  getFridges: Array<Fridge>;
  getItem?: Maybe<Item>;
  getItems: Array<Maybe<Item>>;
};


export type SubscriptionGetCategoryArgs = {
  id: Scalars['ID']['input'];
};


export type SubscriptionGetFridgeArgs = {
  id: Scalars['ID']['input'];
};


export type SubscriptionGetFridgesArgs = {
  page: Scalars['Int']['input'];
  pageSize: Scalars['Int']['input'];
};


export type SubscriptionGetItemArgs = {
  id: Scalars['ID']['input'];
};


export type SubscriptionGetItemsArgs = {
  page: Scalars['Int']['input'];
  pageSize: Scalars['Int']['input'];
};

export type GetCategoriesSubscriptionVariables = Exact<{ [key: string]: never; }>;


export type GetCategoriesSubscription = { __typename?: 'Subscription', getCategories: Array<{ __typename?: 'Category', id: string, name: string } | null> };

export type DeleteCategoryMutationVariables = Exact<{
  id: Scalars['ID']['input'];
}>;


export type DeleteCategoryMutation = { __typename?: 'Mutation', deleteCategory: string };

export type CreateFridgeMutationVariables = Exact<{
  name: Scalars['String']['input'];
}>;


export type CreateFridgeMutation = { __typename?: 'Mutation', createFridge: string };

export type UpdateFridgeMutationVariables = Exact<{
  id: Scalars['ID']['input'];
  name: Scalars['String']['input'];
}>;


export type UpdateFridgeMutation = { __typename?: 'Mutation', updateFridge: string };

export type DeleteFridgeMutationVariables = Exact<{
  id: Scalars['ID']['input'];
}>;


export type DeleteFridgeMutation = { __typename?: 'Mutation', deleteFridge: string };

export type GetFridgesSubscriptionVariables = Exact<{
  page: Scalars['Int']['input'];
  pageSize: Scalars['Int']['input'];
}>;


export type GetFridgesSubscription = { __typename?: 'Subscription', getFridges: Array<{ __typename?: 'Fridge', id: string, name: string }> };


export const GetCategoriesDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"subscription","name":{"kind":"Name","value":"getCategories"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"getCategories"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"name"}}]}}]}}]} as unknown as DocumentNode<GetCategoriesSubscription, GetCategoriesSubscriptionVariables>;
export const DeleteCategoryDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"mutation","name":{"kind":"Name","value":"deleteCategory"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"id"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"ID"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"deleteCategory"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"id"},"value":{"kind":"Variable","name":{"kind":"Name","value":"id"}}}]}]}}]} as unknown as DocumentNode<DeleteCategoryMutation, DeleteCategoryMutationVariables>;
export const CreateFridgeDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"mutation","name":{"kind":"Name","value":"CreateFridge"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"name"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"String"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"createFridge"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"name"},"value":{"kind":"Variable","name":{"kind":"Name","value":"name"}}}]}]}}]} as unknown as DocumentNode<CreateFridgeMutation, CreateFridgeMutationVariables>;
export const UpdateFridgeDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"mutation","name":{"kind":"Name","value":"updateFridge"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"id"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"ID"}}}},{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"name"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"String"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"updateFridge"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"id"},"value":{"kind":"Variable","name":{"kind":"Name","value":"id"}}},{"kind":"Argument","name":{"kind":"Name","value":"name"},"value":{"kind":"Variable","name":{"kind":"Name","value":"name"}}}]}]}}]} as unknown as DocumentNode<UpdateFridgeMutation, UpdateFridgeMutationVariables>;
export const DeleteFridgeDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"mutation","name":{"kind":"Name","value":"deleteFridge"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"id"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"ID"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"deleteFridge"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"id"},"value":{"kind":"Variable","name":{"kind":"Name","value":"id"}}}]}]}}]} as unknown as DocumentNode<DeleteFridgeMutation, DeleteFridgeMutationVariables>;
export const GetFridgesDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"subscription","name":{"kind":"Name","value":"getFridges"},"variableDefinitions":[{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"page"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"Int"}}}},{"kind":"VariableDefinition","variable":{"kind":"Variable","name":{"kind":"Name","value":"pageSize"}},"type":{"kind":"NonNullType","type":{"kind":"NamedType","name":{"kind":"Name","value":"Int"}}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"getFridges"},"arguments":[{"kind":"Argument","name":{"kind":"Name","value":"page"},"value":{"kind":"Variable","name":{"kind":"Name","value":"page"}}},{"kind":"Argument","name":{"kind":"Name","value":"pageSize"},"value":{"kind":"Variable","name":{"kind":"Name","value":"pageSize"}}}],"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"id"}},{"kind":"Field","name":{"kind":"Name","value":"name"}}]}}]}}]} as unknown as DocumentNode<GetFridgesSubscription, GetFridgesSubscriptionVariables>;