import axios from 'axios'
import { PortfolioSummary, Asset, SoldAsset, PortfolioDistribution } from '../types'

const api = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
  },
})

export const portfolioApi = {
  getSummary: async (): Promise<PortfolioSummary> => {
    const response = await api.get('/portfolio/summary')
    return response.data
  },

  getAssets: async (): Promise<Asset[]> => {
    const response = await api.get('/portfolio/assets')
    return response.data
  },

  getSoldAssets: async (): Promise<SoldAsset[]> => {
    const response = await api.get('/portfolio/sold-assets')
    return response.data
  },

  getDistribution: async (): Promise<PortfolioDistribution[]> => {
    const response = await api.get('/portfolio/distribution')
    return response.data
  },

  depositMoney: async (amount: number): Promise<void> => {
    await api.post('/portfolio/deposit', { amount })
  },

  withdrawMoney: async (amount: number): Promise<void> => {
    await api.post('/portfolio/withdraw', { amount })
  },

  updatePrices: async (): Promise<PortfolioSummary> => {
    const response = await api.post('/portfolio/update-prices')
    return response.data
  },
}

